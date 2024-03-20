package model;

public class RideTaken {
    String requester;
    City origin;
    City destination;
    int seats;

    int rideId;

    public RideTaken(String requester, City origin, City destination, int seats, int rideId) {
        this.requester = requester;
        this.origin = origin;
        this.destination = destination;
        this.seats = seats;
        this.rideId = rideId;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }
}