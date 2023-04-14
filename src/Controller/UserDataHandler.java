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
    private static final String USER_PASSWORDS_FILE = "database/User_Password.txt";


    public Map<String, String> loadUserPasswordFromDatabase(List<User> users) throws IOException {
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
    public Map<String, String> loadUserNameFromDatabase(List<User> users) throws IOException {
        Map<String, String> userName = new HashMap<>();
        for (User user : users) {
            userName.get(user.getUserID());
        }
        return userName;
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
