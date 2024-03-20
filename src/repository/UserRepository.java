package repository;

import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import model.Gender;
import model.User;
import model.Vehicle;
import model.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public String addUser(String name, Gender gender, int age) {
        if (this.isRegisteredUser(name))
            throw new UserAlreadyExistsException("User with name " + name + " already exists.");
        User user = new User(name, gender, age);
        users.add(user);
        return "User " + name + " added successfully.";
    }

    public User getUserByName(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean isRegisteredUser(String name) {
        return users.stream().anyMatch(u -> u.getName().equals(name));
    }

    public void validateUser(String name) {
        if (!this.isRegisteredUser(name))
            throw new UserNotFoundException("User with name " + name + " not found. Add user first.");
    }

    public List<String> getUserNames() {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}