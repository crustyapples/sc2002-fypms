package src.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    protected String userID;
    protected String password;
    protected String name;
    protected String email;
    protected List<Request> requests;


    public User(String userID, String password, String name, String email) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.requests = new ArrayList<>();
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public String viewDetails() {
        return "UserID: " + userID + "\nName: " + name + "\nEmail: " + email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getUserID() {
        return this.userID;
    }

    public List<Request> getRequests() {
        return this.requests;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

}

