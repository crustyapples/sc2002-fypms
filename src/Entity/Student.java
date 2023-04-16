package src.Entity;

/**
 * The type Student.
 */
public class Student extends User {
    private Project selectedProject;
    private boolean registered;
    private boolean deRegistered;

    /**
     * Instantiates a new Student.
     *
     * @param userID   the user id
     * @param password the password
     * @param name     the name
     * @param email    the email
     */
    public Student(String userID, String password, String name, String email) {
        super(userID, password, name, email);
        this.registered = false;
    }


    /**
     * Sets selected project.
     *
     * @param project the project
     */
    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        this.registered = true;
        this.deRegistered = false;
    }

    /**
     * Gets selected project.
     *
     * @return the selected project
     */
    public Project getSelectedProject() {
        return this.selectedProject;
    }

    /**
     * Gets registered.
     *
     * @return the registered
     */
    public boolean getRegistered() {
        return this.registered;
    }

    /**
     * Sets registered.
     *
     * @param registered the registered
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * Gets de registered.
     *
     * @return the de registered
     */
    public boolean getDeRegistered() {
        return this.deRegistered;
    }

    /**
     * Sets de registered.
     *
     * @param deRegistered the de registered
     */
    public void setDeRegistered(boolean deRegistered) {
        this.deRegistered = deRegistered;
    }

}


