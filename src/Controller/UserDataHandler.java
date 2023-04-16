package src.Controller;

import src.Entity.FYP_Coordinator;
import src.Entity.Student;
import src.Entity.Supervisor;
import src.Entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User data handler.
 */
public class UserDataHandler {
    private static final String USER_PASSWORDS_FILE = "database/User_Passwords.txt";

    /**
     * Load user password from database map.
     *
     * @param users the users
     * @return the map
     * @throws IOException the io exception
     */
    public Map<String, String> loadUserPasswordFromDatabase(List<User> users) throws IOException {
        Map<String, String> userPasswords = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_PASSWORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\t");
                String userID = data[0];
                String password = data[1];
                userPasswords.put(userID,password);
            }
        }

        return userPasswords;
    }


    /**
     * Save user passwords to database.
     *
     * @param userPasswords the user passwords
     * @throws IOException the io exception
     */
    public void saveUserPasswordsToDatabase(Map<String, String> userPasswords) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_PASSWORDS_FILE))) {
            for (Map.Entry<String, String> entry : userPasswords.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
        }
    }

    /**
     * Update user password.
     *
     * @param userID      the user id
     * @param newPassword the new password
     * @throws IOException the io exception
     */
    public void updateUserPassword(String userID, String newPassword) throws IOException {
        Map<String, String> userPasswords = loadUserPasswordFromDatabase(null); // Load existing user passwords

        // Update password in the map
        if (userPasswords.containsKey(userID)) {
            userPasswords.put(userID, newPassword);
        } else {
            throw new IllegalArgumentException("User ID not found: " + userID);
        }

        saveUserPasswordsToDatabase(userPasswords); // Save updated user passwords to the database
    }


}
