package src.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Supervisor.
 */
public class Supervisor extends User {
    /**
     * The Num projects supervised.
     */
    protected int numProjectsSupervised;
    /**
     * The Projects.
     */
    protected List<Project> projects;

    /**
     * Instantiates a new Supervisor.
     *
     * @param userID   the user id
     * @param name     the name
     * @param email    the email
     * @param password the password
     */
    public Supervisor(String userID, String name, String email, String password) {
        super(userID, password, name, email);
        this.numProjectsSupervised = 0;
        this.projects = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    /**
     * Gets projects.
     *
     * @return the projects
     */
    public List<Project> getProjects() {
        return this.projects;
    }

    /**
     * Gets num projects supervised.
     *
     * @return the num projects supervised
     */
    public int getNumProjectsSupervised() {
        return this.numProjectsSupervised;
    }

    /**
     * Sets num project supervised.
     *
     * @param projectsSupervised the projects supervised
     */
    public void setNumProjectSupervised(int projectsSupervised) {
        this.numProjectsSupervised = projectsSupervised;
    }

    /**
     * Add project.
     *
     * @param project the project
     */
    public void addProject(Project project) {
        projects.add(project);
    }
}

