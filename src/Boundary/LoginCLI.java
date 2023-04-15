package src.Boundary;

import java.io.Console;
import java.util.Scanner;
import java.io.IOException;
import java.util.*;

import src.ConsoleColors;
import src.Controller.UserController;
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
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = userController.loginUser(users,userID,password);

        if (user != null) {
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Login successful!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_UNDERLINED + "Welcome, " + user.getName() + "!" + ConsoleColors.RESET);
            return user;
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "Invalid user ID or password. Please try again!\n" + ConsoleColors.RESET);
            return null;
        }

    }
}
