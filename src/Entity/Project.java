package src.Entity;

/**
 * The type Project.
 */
public class Project {
    private Integer projectID;
    private Supervisor supervisor;
    private Supervisor replacementSupervisor;
    private Student student;
    private String title;
    private ProjectStatus projectStatus;

    /**
     * Instantiates a new Project.
     *
     * @param projectID             the project id
     * @param supervisor            the supervisor
     * @param replacementSupervisor the replacement supervisor
     * @param student               the student
     * @param title                 the title
     * @param projectStatus         the project status
     */
    public Project(Integer projectID, Supervisor supervisor, Supervisor replacementSupervisor, Student student, String title, ProjectStatus projectStatus) {
        this.projectID = projectID;
        this.supervisor = supervisor;
        this.replacementSupervisor = replacementSupervisor;
        this.student = student;
        this.title = title;
        this.projectStatus = projectStatus;
    }

    /**
     * Sets project status.
     *
     * @param newProjectStatus the new project status
     */
    public void setProjectStatus(ProjectStatus newProjectStatus) {
        this.projectStatus = newProjectStatus;
    }

    /**
     * Gets student.
     *
     * @return the student
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Sets student.
     *
     * @param newStudent the new student
     */
    public void setStudent(Student newStudent) {
        this.student = newStudent;
    }

    /**
     * View details string.
     *
     * @return the string
     */
    public String viewDetails() {
        return "ProjectID: " + projectID + "\nTitle: " + title + "\nStatus: " + projectStatus + "\nSupervisor: " + supervisor.name + "\nStudent: " + (student != null ? student.name + "\n" : "Not assigned" + "\n");
    }

    /**
     * Gets supervisor.
     *
     * @return the supervisor
     */
    public Supervisor getSupervisor() {
        return this.supervisor;
    }

    /**
     * Gets replacement supervisor.
     *
     * @return the replacement supervisor
     */
    public Supervisor getReplacementSupervisor() {
        return this.replacementSupervisor;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets project status.
     *
     * @return the project status
     */
    public ProjectStatus getProjectStatus() {
        return this.projectStatus;
    }

    /**
     * Get project id int.
     *
     * @return the int
     */
    public int getProjectID(){
        return this.projectID;
    }

    /**
     * Sets title.
     *
     * @param newTitle the new title
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Sets supervisor.
     *
     * @param newSupervisor the new supervisor
     */
    public void setSupervisor(Supervisor newSupervisor) {
        this.supervisor = newSupervisor;
    }

    /**
     * Sets replacement supervisor.
     *
     * @param newReplacementSupervisor the new replacement supervisor
     */
    public void setReplacementSupervisor(Supervisor newReplacementSupervisor) {
        this.replacementSupervisor = newReplacementSupervisor;
    }

}
