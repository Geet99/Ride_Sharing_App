package service;

import model.City;
import model.Gender;
import model.Vehicle;
import model.VehicleType;
import repository.UserRepository;
import repository.VehicleRepository;

public class UserService {

    private UserRepository userRepository;

    private VehicleRepository vehicleRepository;

    public UserService(UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public String addUser(String name, Gender gender, int age) {
        return userRepository.addUser(name, gender, age);
    }

    public String addVehicle(String userName, VehicleType type, String id) {
        return vehicleRepository.addVehicle(userName, type, id);
    }
}
