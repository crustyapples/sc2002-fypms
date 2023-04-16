package src.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Supervisor class represents a user who is a supervisor and can supervise projects.
 * It extends the User class and inherits its attributes and methods.
 */
public class Supervisor extends User {
    /**
     * The number of projects supervised by the supervisor.
     */
    protected int numProjectsSupervised;
    /**
     * The list of projects supervised by the supervisor.
     */
    protected List<Project> projects;

    /**
     * Instantiates a new Supervisor.
     *
     * @param userID   the user id of the supervisor
     * @param name     the name of the supervisor
     * @param email    the email of the supervisor
     * @param password the password of the supervisor
     */
    public Supervisor(String userID, String name, String email, String password) {
        super(userID, password, name, email);
        this.numProjectsSupervised = 0;
        this.projects = new ArrayList<>();
    }
    /**

     * Returns the name of the supervisor.
     *
     * @return the name of the supervisor
     */

    public String getName() {
        return this.name;
    }

    /**
     * Returns the list of projects supervised by the supervisor.
     *
     * @return the list of projects supervised by the supervisor
     */
    public List<Project> getProjects() {
        return this.projects;
    }

    /**
     * Returns the number of projects supervised by the supervisor.
     *
     * @return the number of projects supervised by the supervisor
     */
    public int getNumProjectsSupervised() {
        return this.numProjectsSupervised;
    }

    /**
     * Sets the number of projects supervised by the supervisor.
     *
     * @param projectsSupervised the number of projects supervised by the supervisor
     */
    public void setNumProjectSupervised(int projectsSupervised) {
        this.numProjectsSupervised = projectsSupervised;
    }

    /**
     * Adds a project to the list of projects supervised by the supervisor.
     *
     * @param project the project to be added
     */
    public void addProject(Project project) {
        projects.add(project);
    }
}

