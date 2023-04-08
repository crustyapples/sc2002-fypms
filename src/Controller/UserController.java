package src.Controller;

import src.Boundary.UserDataHandler;
import src.Entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserController {
    public UserDataHandler userDataHandler;

    public UserController() {
        userDataHandler = new UserDataHandler();
    }

    public User loginUser(List<User> users, Map<String, String> userPasswords, String userID, String password) {
        String storedPassword = userPasswords.get(userID);
        if (storedPassword != null && storedPassword.equals(password)) {
            return findUserByID(users, userID);
        } else {
            return null;
        }
    }

    public void changeUserPassword(Map<String, String> userPasswords, String userID, String newPassword) {
        userPasswords.put(userID, newPassword);
        try {
            userDataHandler.saveUserPasswordsToDatabase(userPasswords);
        } catch (IOException e) {
            System.out.println("Error saving user passwords: " + e.getMessage());
        }
    }

    public void viewUserDetails(User user) {
        System.out.println("User ID: " + user.getUserID());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    private User findUserByID(List<User> users, String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
}
