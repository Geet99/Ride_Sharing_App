package repository;

import exception.NoSuchVehicleException;
import exception.UserNotFoundException;
import exception.VehicleAlreadyExistsException;
import model.User;
import model.Vehicle;
import model.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    UserRepository userRepository;
    private List<Vehicle> vehicles = new ArrayList<>();

    public VehicleRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addVehicle(String userName, VehicleType type, String id) {
        userRepository.validateUser(userName);
        if (this.isRegisteredVehicle(id))
            throw new VehicleAlreadyExistsException("Vehicle with id " + id + " already exists.");
        Vehicle vehicle = new Vehicle(userName, type, id);
        vehicles.add(vehicle);
        return "Vehicle added successfully.";
    }

    public Vehicle getVehicleById(String id) {
        return vehicles.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean isRegisteredVehicle(String id) {
        return vehicles.stream().anyMatch(u -> u.getId().equals(id));
    }

    public Vehicle validateAndGetVehicle(String userName, VehicleType type, String id) {
        Vehicle vehicle = this.getVehicleById(id);
        if (vehicle.equals(new Vehicle(userName, type, id)))
            return vehicle;
        else
            throw new NoSuchVehicleException("Vehicle with these details not found. Kindly register it first.");
    }
}