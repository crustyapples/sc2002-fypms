package src.Entity;

public class Student extends User {
    private Project selectedProject;
    private boolean registered;
    private boolean deRegistered;

    public Student(String userID, String password, String name, String email) {
        super(userID, password, name, email);
        this.registered = false;
    }


    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        this.registered = true;
        this.deRegistered = false;
    }

    public Project getSelectedProject() {
        return this.selectedProject;
    }
    public boolean getRegistered() {
        return this.registered;
    }
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean getDeRegistered() {
        return this.deRegistered;
    }
    public void setDeRegistered(boolean deRegistered) {
        this.deRegistered = deRegistered;
    }

}


