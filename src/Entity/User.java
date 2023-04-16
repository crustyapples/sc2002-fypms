package src.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type User.
 */
public class User {
    /**
     * The User id.
     */
    protected String userID;
    /**
     * The Password.
     */
    protected String password;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Email.
     */
    protected String email;
    /**
     * The Requests.
     */
    protected List<Request> requests;


    /**
     * Instantiates a new User.
     *
     * @param userID   the user id
     * @param password the password
     * @param name     the name
     * @param email    the email
     */
    public User(String userID, String password, String name, String email) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.requests = new ArrayList<>();
    }

    /**
     * Change password.
     *
     * @param newPassword the new password
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * View details string.
     *
     * @return the string
     */
    public String viewDetails() {
        return "UserID: " + userID + "\nName: " + name + "\nEmail: " + email;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Gets requests.
     *
     * @return the requests
     */
    public List<Request> getRequests() {
        return this.requests;
    }

    /**
     * Add request.
     *
     * @param request the request
     */
    public void addRequest(Request request) {
        this.requests.add(request);
    }

}

