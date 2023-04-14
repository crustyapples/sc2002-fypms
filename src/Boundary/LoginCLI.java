package src.Boundary;

import java.io.IOException;
import java.util.*;

import src.Controller.*;
import src.Entity.FYP_Coordinator;
import src.Entity.Student;
import src.Entity.Supervisor;
import src.Entity.User;

public class LoginCLI {
    private UserController userController;
    private Scanner scanner;
    private static final String USER_PASSWORDS_FILE = "database/User_Passwords.txt";
    public LoginCLI(UserController userController) {
        this.userController = userController;
        scanner = new Scanner(System.in);
    }

    public User authenticateUser() {

        System.out.println("User ID: ");
        String userID = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        List<User> users = new ArrayList<>();
        UserDataHandler userdata = new UserDataHandler();
        Map<String, String> usersPassword = new HashMap<>();

        users = userdata.getListOfUsers();


        try{
            usersPassword = userdata.loadUserPasswordFromDatabase(users);
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        User user = userController.loginUser(users,usersPassword,userID,password);
        if (user != null) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid user ID or password.");
            return null;
        }
    }
}
