package src.Entity;

public class Student extends User {
    private Project selectedProject;
    private boolean registered;

    public Student(String userID, String password, String name, String email) {
        super(userID, password, name, email);
        this.registered = false;
    }

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        this.registered = true;
    }

    public Project getSelectedProject() {
        return this.selectedProject;
    }
    public boolean isRegistered() {
        return this.registered;
    }
}


