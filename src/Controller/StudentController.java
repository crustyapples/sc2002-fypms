package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Student controller.
 */
public class StudentController extends UserController implements IStudentController{

    private IRequestController requestController;
    private IProjectController projectController;
    private IStudentDataHandler studentDataHandler;

    /**
     * Instantiates a new Student controller.
     *
     * @param studentDataHandler the student data handler
     * @param projectController  the project controller
     * @param requestController  the request controller
     * @throws IOException the io exception
     */
    public StudentController(IStudentDataHandler studentDataHandler, IProjectController projectController, IRequestController requestController) throws IOException {
        this.projectController = projectController;
        this.studentDataHandler = studentDataHandler;
        this.requestController = requestController;
    }

    /**
     * Get available projects.
     *
     * @param projects the projects
     * @return availableProjects the available projects
     */
    public List<Project> getAvailableProjects(List<Project> projects) {
        List<Project> availableProjects = new ArrayList<>();

        for (Project project : projects) {
            if (project.getProjectStatus() == ProjectStatus.AVAILABLE) {
                if (project.getProjectStatus().equals(ProjectStatus.AVAILABLE)) {
                    availableProjects.add(project);
                }
            }

        }
        return availableProjects;
    }
    /**
     * Select project for students
     *
     * @param student the student
     * @param project the project
     * @param coordinator the FYP coordinator
     * @param requests the requests
     * @param projects the projects
     * @throws IOException the io exception
     */
    public void selectProjectForStudent(Student student, Project project, FYP_Coordinator coordinator, List<Request> requests, List<Project> projects) throws IOException {
        project.setProjectStatus(ProjectStatus.RESERVED);
        projectController.updateProject(projects);

        Request newRequest = requestController.createRequest(student, coordinator, RequestType.REGISTER, project, null, requests);
        student.addRequest(newRequest);
        coordinator.addRequest(newRequest);

    }
    /**
     * View student project.
     *
     * @param student the student
     * @return getSelectedProject the selected project for the student
     */
    public Project viewStudentProject(Student student) {
        return student.getSelectedProject();
    }

    /**
     * Request project title change.
     *
     * @param student the student
     * @param newTitle new title for the project
     * @param requests the requests
     * @throws IOException the io exception
     */
    public void requestProjectTitleChange(Student student, String newTitle, List<Request> requests) throws IOException {
        Request newRequest = requestController.createRequest(student, student.getSelectedProject().getSupervisor(), RequestType.CHANGE_TITLE, student.getSelectedProject(), newTitle, requests);
        student.addRequest(newRequest);
        student.getSelectedProject().getSupervisor().addRequest(newRequest);
    }

    /**
     * Request project de registration
     *
     * @param student the student
     * @param coordinator the coordinator
     * @param requests the requests
     * @throws IOException the io exception
     */
    public void requestProjectDeregistration(Student student, FYP_Coordinator coordinator, List<Request> requests) throws IOException {
        Request newRequest = requestController.createRequest(student, coordinator, RequestType.DEREGISTER, student.getSelectedProject(), null, requests);
        student.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }

    /**
     * Update student.
     *
     * @param student the student
     * @param students the students
     * @throws IOException the io exception
     */
    public void updateStudent(Student student, List<Student> students) throws IOException {
        studentDataHandler.saveStudentsToDatabase(students);
    }
}
