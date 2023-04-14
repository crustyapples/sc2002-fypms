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
    public UserDataHandler userDataHandler;

    public UserController() {
        userDataHandler = new UserDataHandler();
    }

    public User loginUser(List<User> users, Map<String, String> userPasswords, String userID, String password) {
        String storedPassword = userPasswords.get(userID);
        List<User> Coordinators = new ArrayList<>();
        List<User> Students = new ArrayList<>();
        List<User> Supervisors = new ArrayList<>();
        try {
            StudentDataHandler stdata = new StudentDataHandler();
            List<Student> usersStudents = stdata.loadStudentsFromDatabase();
            usersStudents.forEach((student) -> {
                Students.add((User) student);
            });
            if (storedPassword != null && storedPassword.equals(password)) {
                if (findUserByID(Students, userID) != null) {
                    System.out.println("Student login successful!");
                    return findUserByID(Students, userID);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data" + e.getMessage());
        }
        try {
            FYP_CoordinatorDataHandler fypdata = new FYP_CoordinatorDataHandler();
            List<FYP_Coordinator> usersCoordinators = fypdata.loadCoordinatorsFromDatabase();
            usersCoordinators.forEach((coordinator) -> {
                Coordinators.add((User) coordinator);
            });
            if (storedPassword != null && storedPassword.equals(password)) {
                if (findUserByID(Coordinators, userID) != null) {
                    System.out.println("Coordinator login successful!");
                    return findUserByID(Coordinators, userID);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data" + e.getMessage());
        }
        try {
            SupervisorDataHandler svdata = new SupervisorDataHandler();
            List<Supervisor> supervisors = svdata.loadFacultyFromDatabase();
            supervisors.forEach((supervisor) -> {
                Supervisors.add((User) supervisor);
            });
            if (storedPassword != null && storedPassword.equals(password)) {
                if (findUserByID(Supervisors, userID) != null) {
                    System.out.println("Supervisor login successful!");
                    return findUserByID(Supervisors, userID);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data" + e.getMessage());
        }

        // break
        return null;
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
