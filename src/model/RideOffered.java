package model;

import java.util.Objects;

public class RideOffered {
    int id;
    City origin;
    int availableSeats;
    Vehicle vehicle;
    City destination;
    boolean taken;
    RideStatus status;

    public RideOffered(int id, City origin, int availableSeats, Vehicle vehicle, City destination) {
        this.id = id;
        this.origin = origin;
        this.availableSeats = availableSeats;
        this.vehicle = vehicle;
        this.destination = destination;
        this.status = RideStatus.STARTED;
        this.taken = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "RideOffered{" +
                "origin=" + origin +
                ", availableSeats=" + availableSeats +
                ", vehicle=" + vehicle +
                ", destination=" + destination +
                '}';
    }
}