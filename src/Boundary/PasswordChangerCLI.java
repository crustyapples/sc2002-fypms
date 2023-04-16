package src.Boundary;

import src.Controller.IUserController;
import src.Entity.User;

import java.io.IOException;
import java.util.Scanner;

/**
 * Constructs a new PasswordChangerCLI object.
 */
public class PasswordChangerCLI {
    /**
     * The user whose password will be changed
     */
    private User user;
    /**
     * The controller for user-related functionality
     */
    private IUserController userController;
    /**
     * The scanner object used to read input from the user
     */
    private Scanner scanner;

    /**
     * Instantiates a new Password changer cli.
     *
     * @param user           the user
     * @param userController the user controller
     */
    public PasswordChangerCLI(User user, IUserController userController) {
        this.user = user;
        this.userController = userController;
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user to enter a new password, changes the user's password, and logs them out.
     *
     * @throws IOException if there is an error changing the user's password
     */
    public void changePassword() throws IOException {
        System.out.println("Enter new password: ");
        String newPassword = scanner.nextLine();
        String userID = user.getUserID();
        userController.changeUserPassword(userID, newPassword);
        System.out.println("You will now be logged out. Please login again!");
    }

    // Additional methods for login and authentication can be added here, if needed.
}
