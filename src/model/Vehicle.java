package model;

import java.util.Objects;

public class Vehicle {
    private String owner;
    private VehicleType type;
    private String id;
    boolean rideOffered;

    public Vehicle(String owner, VehicleType type, String id) {
        this.owner = owner;
        this.type = type;
        this.id = id;
        this.rideOffered = false;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRideOffered() {
        return rideOffered;
    }

    public void setRideOffered(boolean rideOffered) {
        this.rideOffered = rideOffered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(owner, vehicle.owner) && type == vehicle.type && Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, type, id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "owner='" + owner + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}