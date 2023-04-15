package src.Controller;

import src.Entity.FYP_Coordinator;
import src.Entity.Student;
import src.Entity.Supervisor;
import src.Entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {
    protected UserDataHandler userDataHandler;


    public UserController() {
        userDataHandler = new UserDataHandler();
    }

    public User loginUser(List<User> users, String userID, String password) throws IOException {
        Map<String, String> userPasswords = userDataHandler.loadUserPasswordFromDatabase(users);
        String storedPassword = userPasswords.get(userID);
        if (storedPassword != null && storedPassword.equals(password)) {
            if (findUserByID(users,userID) != null) {
                User user = findUserByID(users,userID);
                return user;
            }
        }

        return null;
    }

    public void changeUserPassword(String userID, String newPassword) {
        try {
            userDataHandler.updateUserPassword(userID, newPassword);
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
