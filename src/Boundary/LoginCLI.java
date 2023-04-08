package src.Boundary;

import java.util.Scanner;

import src.Controller.UserController;
import src.Entity.User;
import java.util.List;
import java.util.Map;

public class LoginCLI {
    private UserController userController;
    private Scanner scanner;

    public LoginCLI(UserController userController) {
        this.userController = userController;
        scanner = new Scanner(System.in);
    }

    public User authenticateUser(List<User> users, Map<String, String> userPasswords) {
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = userController.loginUser(users, userPasswords, userID, password);
        if (user != null) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid user ID or password.");
            return null;
        }
    }
}
