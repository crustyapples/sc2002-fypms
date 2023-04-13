package src.Entity;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends User {
    protected int numProjectsSupervised;
    protected List<Project> projects;

    public Supervisor(String userID, String name, String email, String password) {
        super(userID, password, name, email);
        this.numProjectsSupervised = 0;
        this.projects = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public int getNumProjectsSupervised() {
        return this.numProjectsSupervised;
    }

    public void setNumProjectSupervised(int projectsSupervised) {
        this.numProjectsSupervised = projectsSupervised;
    }

    public void addProject(Project project) {
        projects.add(project);
    }
}

