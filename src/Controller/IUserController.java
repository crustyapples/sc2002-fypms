package src.Controller;

import src.Entity.Request;
import src.Entity.User;

import java.io.IOException;
import java.util.List;

/**
 * The interface User controller.
 */
public interface IUserController {
    /**
     * Login user user.
     *
     * @param users    the users
     * @param userID   the user id
     * @param password the password
     * @return the user
     * @throws IOException the io exception
     */
    User loginUser(List<User> users, String userID, String password) throws IOException;

    /**
     * Change user password.
     *
     * @param userID      the user id
     * @param newPassword the new password
     */
    void changeUserPassword(String userID, String newPassword);

    /**
     * View user details.
     *
     * @param user the user
     */
    void viewUserDetails(User user);

    /**
     * Gets request history.
     *
     * @param user the user
     * @return the request history
     */
    List<Request> getRequestHistory(User user);
}
