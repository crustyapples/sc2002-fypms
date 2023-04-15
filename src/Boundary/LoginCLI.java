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

    public void loginMenu() {

        System.out.println("1. Login");
        System.out.println("2. Logout and Shutdown");
        System.out.println("0. Exit");
    }

    public User authenticateUser() throws IOException {
        boolean exit = false;
        while(!exit) {
            loginMenu();
            System.out.print("Enter your choice: ");
            int loginChoice = scanner.nextInt();
            scanner.nextLine();

            switch (loginChoice) {
                case 1:
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
                        break;
                    }
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BRIGHT +"Invalid choice! Please try again!\n" + ConsoleColors.RESET);
                    break;

            }

        }

        return null;
    }
}
