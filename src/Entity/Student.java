package src.Entity;

public class Student extends User {
    private Project selectedProject;
    private boolean hasProject;

    public Student(String userID, String password, String name, String email) {
        super(userID, password, name, email);
        this.hasProject = false;
    }

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        this.hasProject = true;
    }

    public Project getSelectedProject() {
        return this.selectedProject;
    }
    public boolean isHasProject() {
        return this.hasProject;
    }
}


