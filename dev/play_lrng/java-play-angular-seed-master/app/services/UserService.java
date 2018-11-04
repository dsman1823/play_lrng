package services;

import models.User;

import java.util.List;

public class UserService {

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User createUser(User user) {
        user.save();
        return user;
    }

    public User getUser(Long id) {
        return User.find.byId(id);
    }

    public List<User> getAllUsers() {
        return User.find.all();
    }

    public boolean doesUserExist(Long id) {
        return !(User.find.byId(id) == null);
    }

    public void deleteUser(Long id) {
        User.find.deleteById(id);
    }

    public boolean doesUserExist(User user) {
        return getAllUsers().stream()
                .anyMatch(u -> user.equals(u));
    }
}
