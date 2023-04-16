package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type User controller.
 */
public class UserController implements IUserController{
    /**
     * The User data handler.
     */
    protected UserDataHandler userDataHandler;


    /**
     * Instantiates a new User controller.
     */
    public UserController() {
        userDataHandler = new UserDataHandler();
    }

    /**
     * Login user.
     * @param users the users
     * @param userID the userID
     * @param password password
     * @return user the user
     * @throws IOException the io exception
     */
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

    /**
     * Change user password.
     * @param userID the userID
     * @param newPassword new password
     */
    public void changeUserPassword(String userID, String newPassword) {
        try {
            userDataHandler.updateUserPassword(userID, newPassword);
        } catch (IOException e) {
            System.out.println("Error saving user passwords: " + e.getMessage());
        }
    }
    /**
     * View user details.
     * @param user the user
     */
    public void viewUserDetails(User user) {
        System.out.println("User ID: " + user.getUserID());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    /**
     * Get request history.
     * @param user the user
     * @return requestHistory the request history
     */
    public List<Request> getRequestHistory(User user) {
        List<Request> requestHistory = new ArrayList<>();
        for (Request request : user.getRequests()) {
                requestHistory.add(request);
        }
        return requestHistory;
    }

    /**
     * Find user by ID.
     * @param users the users
     * @param userID the userID
     * @return user the user
     */
    private User findUserByID(List<User> users, String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
}
