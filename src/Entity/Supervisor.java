package src.Entity;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends User {
    protected int numProjects;
    protected List<Project> projects;

    public Supervisor(String userID, String name, String email, String password) {
        super(userID, password, name, email);
        this.numProjects = 0;
        this.projects = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Project> getProjects() {
        return this.projects;
    }
}

