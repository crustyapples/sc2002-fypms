package src.Controller;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupervisorController extends UserController{
    protected IProjectController projectController;
    protected IRequestController requestController;
    protected IStudentController studentController;

    public SupervisorController(IStudentController studentController, IProjectController projectController, IRequestController requestController) throws IOException {
        this.projectController = projectController;
        this.studentController = studentController;
        this.requestController = requestController;

    }

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

    public Project updateTitle(Project project, String newTitle, List<Project> projects) throws IOException {
        project.setTitle(newTitle);
        projectController.updateProject(projects);
        return project;
    }

    public void viewSupervisorProjects(Supervisor supervisor) {
        for (Project project : supervisor.getProjects()) {
            System.out.println(project.viewDetails());
        }
    }

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

    public boolean checkSupervisorAvailability(Supervisor supervisor, List<Project> projects) throws IOException {

        if (supervisor.getNumProjectsSupervised() == 2) {
            return false;
        }

        return true;
    }

    public void approveRequest(Request request, List<Request> requests) throws IOException {
        requestController.approveRequest(request, requests);

    }

    public void rejectRequest(Request request, List<Request> requests) throws IOException {
        requestController.rejectRequest(request, requests);
    }

    public void requestStudentTransferToAnotherSupervisor(Supervisor supervisor, Project project, Supervisor newSupervisor, FYP_Coordinator coordinator,List<Request> requests, List<Project> projects) throws IOException {
        project.setReplacementSupervisor(newSupervisor);
        projectController.updateProject(projects);

        Request newRequest = requestController.createRequest(supervisor, coordinator, RequestType.TRANSFER_STUDENT, project,newSupervisor.getUserID(),requests);
        supervisor.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }

}
