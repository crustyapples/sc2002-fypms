package src.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The User class represents a user of FYPMS.
 */
public class User {
    /**
     * The User id.
     */
    protected String userID;
    /**
     * The user's password.
     */
    protected String password;
    /**
     * The user's name.
     */
    protected String name;
    /**
     * The user's email.
     */
    protected String email;
    /**
     * The list of requests made by the user.
     */
    protected List<Request> requests;


    /**
     * Constructs a new User object with the given user ID, password, name, and email.
     *
     * @param userID   the user's ID
     * @param password the user's password
     * @param name     the user's name
     * @param email    the user's email
     */
    public User(String userID, String password, String name, String email) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.requests = new ArrayList<>();
    }

    /**
     * Changes the user's password to the given password.
     *
     * @param newPassword the new password
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Returns a string representation of the user's details.
     *
     * @return a string containing the user's ID, name, and email
     */
    public String viewDetails() {
        return "UserID: " + userID + "\nName: " + name + "\nEmail: " + email;
    }

    /**
     * Returns the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the user's ID.
     *
     * @return the user's ID
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Returns the list of requests made by the user.
     *
     * @return the list of requests
     */
    public List<Request> getRequests() {
        return this.requests;
    }

    /**
     * Adds a request to the user's list of requests.
     *
     * @param request the request to add
     */
    public void addRequest(Request request) {
        this.requests.add(request);
    }

}

