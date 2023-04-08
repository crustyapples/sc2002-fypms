package src.Boundary;

import src.Entity.User;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataHandler {
    private static final String USER_PASSWORDS_FILE = "database/User_Passwords.txt";

    public Map<String, String> loadUserPasswordsFromDatabase(List<User> users) throws IOException {
        Map<String, String> userPasswords = new HashMap<>();
        for (User user : users) {
            userPasswords.put(user.getUserID(), "password");
        }
        return userPasswords;
    }

    public void saveUserPasswordsToDatabase(Map<String, String> userPasswords) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_PASSWORDS_FILE))) {
            for (Map.Entry<String, String> entry : userPasswords.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
        }
    }
}
