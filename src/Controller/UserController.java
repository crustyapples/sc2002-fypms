package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController implements IUserController{
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

    public List<Request> getRequestHistory(User user) {
        List<Request> requestHistory = new ArrayList<>();
        for (Request request : user.getRequests()) {
                requestHistory.add(request);
        }
        return requestHistory;
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
