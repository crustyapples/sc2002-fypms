package src.Entity;

/**
 * The Student class represents a student user.
 * It extends the User class and has additional fields and methods specific to students.
 */
public class Student extends User {
    /**
     * The project that the student has selected.
     */
    private Project selectedProject;
    /**
     * Whether the student is currently registered in a project.
     */
    private boolean registered;
    /**
     * Whether the student has been deregistered from a project.
     */
    private boolean deRegistered;

    /**
     * Instantiates a new Student.
     *
     * @param userID   the ID of the student user.
     * @param password the password of the student user.
     * @param name     the name of the student user.
     * @param email    the email of the student user.
     */
    public Student(String userID, String password, String name, String email) {
        super(userID, password, name, email);
        this.registered = false;
    }


    /**
     * Sets the project that the student has selected.
     *
     * @param project the project that the student has selected.
     */
    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        this.registered = true;
        this.deRegistered = false;
    }

    /**
     * Returns the project that the student has selected.
     *
     * @return the project that the student has selected.
     */
    public Project getSelectedProject() {
        return this.selectedProject;
    }

    /**
     * Returns whether the student is currently registered in a project.
     *
     * @return whether the student is currently registered in a project.
     */
    public boolean getRegistered() {
        return this.registered;
    }

    /**
     * Sets whether the student is currently registered in a project.
     *
     * @param registered whether the student is currently registered in a project.
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * Returns whether the student has been deregistered from a project.
     *
     * @return whether the student has been deregistered from a project.
     */
    public boolean getDeRegistered() {
        return this.deRegistered;
    }

    /**
     * Sets whether the student has been deregistered from a project.
     *
     * @param deRegistered whether the student has been deregistered from a project.
     */
    public void setDeRegistered(boolean deRegistered) {
        this.deRegistered = deRegistered;
    }

}


