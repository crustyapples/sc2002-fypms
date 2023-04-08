package src.Entity;

public class Project {
    private int projectID;
    private Supervisor supervisor;
    private Student student;
    private String title;
    private String status;

    public Project(Supervisor supervisor, Student student, String title, String status) {
        this.projectID = projectID;
        this.supervisor = supervisor;
        this.student = student;
        this.title = title;
        this.status = status;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public void setStudent(Student newStudent) {
        this.student = newStudent;
    }

    public String viewDetails() {
        return "ProjectID: " + projectID + "\nTitle: " + title + "\nStatus: " + status + "\nSupervisor: " + supervisor.name + "\nStudent: " + (student != null ? student.name : "Not assigned");
    }

    public Supervisor getSupervisor() {
        return this.supervisor;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStatus() {
        return this.status;
    }

    public int getProjectID(){
        return this.projectID;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setSupervisor(Supervisor newSupervisor) {
        this.supervisor = newSupervisor;
    }
}
