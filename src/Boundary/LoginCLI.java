package src.Boundary;

import java.io.IOException;
import java.util.*;

import src.Controller.*;
import src.Entity.User;

public class LoginCLI {
    private UserController userController;
    private List<User> users;
    private Map<String, String> userPasswords;
    private Scanner scanner;
    public LoginCLI(UserController userController, List<User> users) {
        this.userController = userController;
        this.users = users;
        scanner = new Scanner(System.in);
    }

    public User authenticateUser() throws IOException {

        System.out.println("User ID: ");
        String userID = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        User user = userController.loginUser(users,userID,password);

        if (user != null) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid user ID or password.");
            return null;
        }

    }
}
