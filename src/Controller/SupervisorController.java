package src.Controller;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Supervisor controller.
 */
public class SupervisorController extends UserController{
    /**
     * The Project controller.
     */
    protected IProjectController projectController;
    /**
     * The Request controller.
     */
    protected IRequestController requestController;
    /**
     * The Student controller.
     */
    protected IStudentController studentController;

    /**
     * Instantiates a new Supervisor controller.
     *
     * @param studentController the student controller
     * @param projectController the project controller
     * @param requestController the request controller
     * @throws IOException the io exception
     */
    public SupervisorController(IStudentController studentController, IProjectController projectController, IRequestController requestController) throws IOException {
        this.projectController = projectController;
        this.studentController = studentController;
        this.requestController = requestController;

    }

    /**
     * Create project project.
     *
     * @param supervisor the supervisor
     * @param title      the title
     * @param projects   the projects
     * @return the project
     * @throws IOException the io exception
     */
    public Project createProject(Supervisor supervisor, String title, List<Project> projects) throws IOException {

        Project project = projectController.createProject(title, projects, supervisor);
        supervisor.addProject(project);

        if (project.getSupervisor().getNumProjectsSupervised() == 2) {

            for (Project supervisorProject : project.getSupervisor().getProjects()) {
                if (supervisorProject.getProjectStatus() != ProjectStatus.ALLOCATED) {
                    project.setProjectStatus(ProjectStatus.UNAVAILABLE);
                    projectController.updateProject(projects);
                }
            }
        }

        return project;
    }

    /**
     * Update title project.
     *
     * @param project  the project
     * @param newTitle the new title
     * @param projects the projects
     * @return the project
     * @throws IOException the io exception
     */
    public Project updateTitle(Project project, String newTitle, List<Project> projects) throws IOException {
        project.setTitle(newTitle);
        projectController.updateProject(projects);
        return project;
    }

    /**
     * View supervisor projects.
     *
     * @param supervisor the supervisor
     */
    public void viewSupervisorProjects(Supervisor supervisor) {
        for (Project project : supervisor.getProjects()) {
            System.out.println(project.viewDetails());
        }
    }

    /**
     * Gets incoming requests.
     *
     * @param user the user
     * @return the incoming requests
     */
    public List<Request> getIncomingRequests(User user) {
        List <Request> incomingRequests = new ArrayList<>();
        for (Request request : user.getRequests()) {
            if (request.getSender() instanceof Student) {
                incomingRequests.add(request);
            }
        }
        return incomingRequests;
    }
    public List<Request> getRequestHistory(User user) {
        List<Request> requestHistory = new ArrayList<>();
        for (Request request : user.getRequests()) {
            if (request.getSender() instanceof Supervisor) {
                requestHistory.add(request);
            }
        }
        return requestHistory;
    }

    /**
     * Check supervisor availability boolean.
     *
     * @param supervisor the supervisor
     * @param projects   the projects
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean checkSupervisorAvailability(Supervisor supervisor, List<Project> projects) throws IOException {

        if (supervisor.getNumProjectsSupervised() == 2) {
            return false;
        }

        return true;
    }

    /**
     * Approve request.
     *
     * @param request  the request
     * @param requests the requests
     * @throws IOException the io exception
     */
    public void approveRequest(Request request, List<Request> requests) throws IOException {
        requestController.approveRequest(request, requests);

    }

    /**
     * Reject request.
     *
     * @param request  the request
     * @param requests the requests
     * @throws IOException the io exception
     */
    public void rejectRequest(Request request, List<Request> requests) throws IOException {
        requestController.rejectRequest(request, requests);
    }

    /**
     * Request student transfer to another supervisor.
     *
     * @param supervisor    the supervisor
     * @param project       the project
     * @param newSupervisor the new supervisor
     * @param coordinator   the coordinator
     * @param requests      the requests
     * @param projects      the projects
     * @throws IOException the io exception
     */
    public void requestStudentTransferToAnotherSupervisor(Supervisor supervisor, Project project, Supervisor newSupervisor, FYP_Coordinator coordinator,List<Request> requests, List<Project> projects) throws IOException {
        project.setReplacementSupervisor(newSupervisor);
        projectController.updateProject(projects);

        Request newRequest = requestController.createRequest(supervisor, coordinator, RequestType.TRANSFER_STUDENT, project,newSupervisor.getUserID(),requests);
        supervisor.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }

}
