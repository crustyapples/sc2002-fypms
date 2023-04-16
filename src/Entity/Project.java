package src.Entity;

/**
 * This class represents a project that can be supervised by a supervisor and assigned to a student.
 */
public class Project {
    /**
     * The ID of the project.
     */
    private Integer projectID;
    /**
     * The supervisor of the project.
     */
    private Supervisor supervisor;
    /**
     * The replacement supervisor of the project.
     */
    private Supervisor replacementSupervisor;
    /**
     * The student assigned to the project.
     */
    private Student student;
    /**
     * The title of the project.
     */
    private String title;
    /**
     * The status of the project.
     */
    private ProjectStatus projectStatus;

    /**
     * Instantiates a new Project, with the given attributes.
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
     * This method sets the project status.
     *
     * @param newProjectStatus the new project status
     */
    public void setProjectStatus(ProjectStatus newProjectStatus) {
        this.projectStatus = newProjectStatus;
    }

    /**
     * This method returns the student allocated to the project.
     *
     * @return the student
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * This method sets the new student to the project.
     *
     * @param newStudent the new student
     */
    public void setStudent(Student newStudent) {
        this.student = newStudent;
    }

    /**
     * This method returns a string containing the details of the project.
     *
     * @return the string containing the details of the project
     */
    public String viewDetails() {
        return "ProjectID: " + projectID + "\nTitle: " + title + "\nStatus: " + projectStatus + "\nSupervisor: " + supervisor.name + "\nStudent: " + (student != null ? student.name + "\n" : "Not assigned" + "\n");
    }

    /**
     * This method gets the supervisor of the project.
     *
     * @return the supervisor of the project
     */
    public Supervisor getSupervisor() {
        return this.supervisor;
    }

    /**
     * This method gets the replacement supervisor.
     *
     * @return the replacement supervisor
     */
    public Supervisor getReplacementSupervisor() {
        return this.replacementSupervisor;
    }

    /**
     * This method gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This method gets project status.
     *
     * @return the project status
     */
    public ProjectStatus getProjectStatus() {
        return this.projectStatus;
    }

    /**
     * This method get project id int.
     *
     * @return the int
     */
    public int getProjectID(){
        return this.projectID;
    }

    /**
     * This method returns the new project title.
     *
     * @param newTitle the new title
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * This method returns the new supervisor.
     *
     * @param newSupervisor the new supervisor
     */
    public void setSupervisor(Supervisor newSupervisor) {
        this.supervisor = newSupervisor;
    }

    /**
     * This method returns the new replacement supervisor.
     *
     * @param newReplacementSupervisor the new replacement supervisor
     */
    public void setReplacementSupervisor(Supervisor newReplacementSupervisor) {
        this.replacementSupervisor = newReplacementSupervisor;
    }

}
