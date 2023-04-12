package src.Entity;

public class Project {
    private Integer projectID;
    private Supervisor supervisor;
    private Supervisor replacementSupervisor;
    private Student student;
    private String title;
    private ProjectStatus projectStatus;

    public Project(Integer projectID, Supervisor supervisor, Student student, String title, ProjectStatus projectStatus) {
        this.projectID = projectID;
        this.supervisor = supervisor;
        this.replacementSupervisor = null;
        this.student = student;
        this.title = title;
        this.projectStatus = projectStatus;
    }

    public void setProjectStatus(ProjectStatus newProjectStatus) {
        this.projectStatus = newProjectStatus;
    }

    public void setStudent(Student newStudent) {
        this.student = newStudent;
    }

    public String viewDetails() {
        return "ProjectID: " + projectID + "\nTitle: " + title + "\nStatus: " + projectStatus + "\nSupervisor: " + supervisor.name + "\nStudent: " + (student != null ? student.name : "Not assigned");
    }
    public Supervisor getSupervisor() {
        return this.supervisor;
    }
    public Supervisor getReplacementSupervisor() {
        return this.replacementSupervisor;
    }

    public String getTitle() {
        return this.title;
    }

    public ProjectStatus getProjectStatus() {
        return this.projectStatus;
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
    public void setReplacementSupervisor(Supervisor newReplacementSupervisor) {
        this.replacementSupervisor = newReplacementSupervisor;
    }
}
