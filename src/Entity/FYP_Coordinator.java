package src.Entity;

/**
 * This class contains all the information of a Fyp coordinator.
 */
public class FYP_Coordinator extends Supervisor {
    /**
     * Instantiates a new Fyp coordinator.
     *
     * @param userID   the user id
     * @param password the password
     * @param name     the name
     * @param email    the email
     */
    public FYP_Coordinator(String userID, String password, String name, String email) {
        super(userID, password, name, email);
    }
}

