package service;

import exception.NoSuchRideException;
import model.*;
import output.ConsolePrint;
import output.Print;
import repository.UserRepository;
import repository.VehicleRepository;
import util.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class RideService {
    private static int rideCounter = 0;

    private UserRepository userRepository;

    private VehicleRepository vehicleRepository;

    Print printer = new ConsolePrint();
    Map<Integer, RideOffered> ridesOffered = new HashMap<>();

    List<RideTaken> ridesTaken = new ArrayList<>();

    List<Integer> eligibleSeatRequests = Arrays.asList(1, 2);

    public RideService(UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public String offerRide(String userName, City origin, int availableSeats, VehicleType vehicleType, String vehicleId, City destination) {
        userRepository.validateUser(userName);
        Vehicle vehicle = vehicleRepository.validateAndGetVehicle(userName, vehicleType, vehicleId);
        if (vehicle.isRideOffered())
            return "Can't add ride as a ride is already offered on this vehicle.";
        int rideId = ++rideCounter;
        ridesOffered.put(rideId, new RideOffered(rideId, origin, availableSeats, vehicle, destination));
        vehicle.setRideOffered(true);
        return "Ride added successfully.";
    }

    public String selectRide(String rider, City origin, City destination, int reqSeats, String selectionStrategy) {
        userRepository.validateUser(rider);
        if (!eligibleSeatRequests.contains(reqSeats))
            return "ERROR: User can only request upto 2 seats.";

        if (selectionStrategy.equals(Constants.MOST_VACANT)) {
            RideOffered eligibleRide = this.getEligibleRides(origin, destination, reqSeats)
                    .stream()
                    .max(Comparator.comparing(RideOffered::getAvailableSeats))
                    .orElse(null);

            if (eligibleRide == null) {
                return "No eligible rides found.";
            }

            ridesTaken.add(new RideTaken(rider, origin, destination, reqSeats, eligibleRide.getId()));
            this.markRideTaken(eligibleRide.getId(), reqSeats);
            return "Found eligible ride " + eligibleRide.getId();
        } else
            return "Selection Strategy unknown.";
    }

    public String selectRide(String rider, City origin, City destination, int reqSeats, VehicleType preferredVehicle) {
        userRepository.validateUser(rider);
        if (!eligibleSeatRequests.contains(reqSeats))
            return "ERROR: User can only request upto 2 seats.";

        RideOffered eligibleRide = this.getEligibleRides(origin, destination, reqSeats)
                .stream()
                .filter(r -> r.getVehicle().getType().equals(preferredVehicle))
                .findFirst()
                .orElse(null);

        if (eligibleRide == null) {
            return "No eligible rides found.";
        }

        ridesTaken.add(new RideTaken(rider, origin, destination, reqSeats, eligibleRide.getId()));
        this.markRideTaken(eligibleRide.getId(), reqSeats);
        return "Found eligible ride " + eligibleRide.getId();
    }

    public String endRide(int rideId) {
        if (!ridesOffered.containsKey(rideId))
            throw new NoSuchRideException("Ride with " + rideId + " doesn't exist.");
        RideOffered ride = ridesOffered.get(rideId);
        ride.setStatus(RideStatus.ENDED);
        ridesOffered.put(rideId, ride);
        return "Ride " + rideId + " ended successfully";
    }

    public void printRideStats() {
        List<String> users = userRepository.getUserNames();
        for (String user : users) {
            int taken = this.getTotalRidesTakenByUser(user);
            int offered = this.getTotalRidesOfferedByUser(user);
            printer.print(String.format(Constants.RIDE_STATS, user, taken, offered));
        }
    }

    private int getTotalRidesOfferedByUser(String user) {
        return (int) ridesOffered.values().stream().filter(r -> r.getVehicle().getOwner().equals(user) && r.getStatus().equals(RideStatus.ENDED)).count();
    }

    private int getTotalRidesTakenByUser(String user) {
        return (int) ridesTaken.stream().filter(r -> r.getRequester().equals(user) && ridesOffered.get(r.getRideId()).getStatus().equals(RideStatus.ENDED)).count();
    }

    private List<RideOffered> getEligibleRides(City origin, City destination, int reqSeats) {
        return ridesOffered.values()
                .stream()
                .filter(r -> r.getOrigin().equals(origin)
                        && r.getDestination().equals(destination)
                        && r.getAvailableSeats() >= reqSeats
                        && !r.isTaken())
                .collect(Collectors.toList());
    }

    private void markRideTaken(int rideId, int reqSeats) {
        RideOffered ride = ridesOffered.get(rideId);
        ride.setAvailableSeats(ride.getAvailableSeats() - reqSeats);

        if (ride.getAvailableSeats() == 0)
            ride.setTaken(true);
        ridesOffered.put(rideId, ride);
    }
}