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

public class UserDataHandler {
    private static final String USER_PASSWORDS_FILE = "database/User_Passwords.txt";

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


    public void saveUserPasswordsToDatabase(Map<String, String> userPasswords) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_PASSWORDS_FILE))) {
            for (Map.Entry<String, String> entry : userPasswords.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
        }
    }

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

    public List<User> getListOfUsers () {
        List<User> users = new ArrayList<>();
        try {
            StudentDataHandler stdata = new StudentDataHandler();
            List<Student> students = stdata.loadStudentsFromDatabase();
            students.forEach((student) -> {
                users.add((User) student);
            });
        } catch (IOException e) {
            System.out.println("Error loading student data" + e.getMessage());
        }

        try {
            FYP_CoordinatorDataHandler fypdata = new FYP_CoordinatorDataHandler();
            List<FYP_Coordinator> coordinators = fypdata.loadCoordinatorsFromDatabase();
            coordinators.forEach((coordinator) -> {
                users.add((User) coordinator);
            });
        } catch (IOException e) {
            System.out.println("Error loading student data" + e.getMessage());
        }

        try {
            SupervisorDataHandler svdata = new SupervisorDataHandler();
            List<Supervisor> supervisors = svdata.loadFacultyFromDatabase();
            supervisors.forEach((supervisor) -> {
                users.add((User) supervisor);
            });
        } catch (IOException e) {
            System.out.println("Error loading student data" + e.getMessage());
        }
        return users;
    }


}
