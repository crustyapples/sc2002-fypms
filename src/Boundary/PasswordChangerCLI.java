package src.Boundary;

import src.Controller.IUserController;
import src.Entity.User;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Password changer cli.
 */
public class PasswordChangerCLI {
    private User user;
    private IUserController userController;
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
     * Change password.
     *
     * @throws IOException the io exception
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