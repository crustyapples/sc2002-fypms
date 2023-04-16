package src.Boundary;

import java.io.Console;
import java.util.Scanner;
import java.io.IOException;
import java.util.*;

import src.ConsoleColors;
import src.Controller.UserController;
import src.Controller.*;
import src.CustomExceptions.InvalidInputException;
import src.Entity.*;

/**
 * The LoginCLI class represents the command-line interface for the user login functionality.
 */
public class LoginCLI {
    /**
     * The UserController instance used to handle user operations
     */
    private UserController userController;
    /**
     * The list of User objects in the database
     */
    private List<User> users;
    /**
     * map of user IDs to passwords
     */
    private Map<String, String> userPasswords;
    /**
     * The Scanner instance used to read user input
     */
    private Scanner scanner;

    /**
     * Constructs a new LoginCLI object with the specified UserController and list of User objects.
     *
     * @param userController The UserController instance used to handle user operations.
     * @param users The list of User objects in the system.
     */
    public LoginCLI(UserController userController, List<User> users) {
        this.userController = userController;
        this.users = users;
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the login menu options.
     */
    public void loginMenu() {

        System.out.println("1. Login");
        System.out.println("0. Logout and Shutdown");
    }

    /**
     * Authenticates a user by prompting for their user ID and password.
     *
     * @return The User object associated with the authenticated user, or null if no user was authenticated.
     * @throws IOException If an error occurs while reading user input.
     */
    public User authenticateUser() throws IOException {
        boolean exit = false;
        while(!exit) {
            loginMenu();
            try {
                System.out.print("Enter your choice: ");
                int loginChoice = scanner.nextInt();
                scanner.nextLine();

                switch (loginChoice) {
                    case 1:
                        int userCheck = -1;
                        while (userCheck == -1) {
                            System.out.println("User ID: ");
                            String userID = scanner.nextLine();
                            System.out.print("Enter your password: ");
                            String password = scanner.nextLine();
                            User user = userController.loginUser(users,userID,password);


                            try {
                                if (user != null) {
                                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Login successful!" + ConsoleColors.RESET);
                                    System.out.println(ConsoleColors.BLUE_UNDERLINED + "Welcome, " + user.getName() + "!" + ConsoleColors.RESET);
                                    return user;
                                } else {
                                    throw new InvalidInputException.InvalidUserIDPasswordException();
                                }

                            } catch(InvalidInputException.InvalidUserIDPasswordException e) {
                                userCheck = -1;
                                System.out.println(e.getMessage());
                            }

                        }
                    case 2:
                        System.out.println("Goodbye!");
                        exit = true;
                        break;

                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_BRIGHT +"Invalid choice! Please try again!\n" + ConsoleColors.RESET);
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please input an integer!\n"+ ConsoleColors.RESET);
                scanner.next(); //Clear the invalid input
            }


        }

        return null;
    }


}
