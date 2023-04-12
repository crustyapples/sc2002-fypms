package src.Controller;
import src.Entity.*;

import java.io.IOException;
import java.util.List;

public class SupervisorController extends UserController{
    protected ProjectController projectController;
    protected RequestController requestController;

    public SupervisorController() throws IOException {

        projectController = new ProjectController();
        requestController = new RequestController();
    }

    public Project createProject(Supervisor supervisor, String title, List<Project> projects) throws IOException {
        Project project = projectController.createProject(title, projects, supervisor);
        supervisor.addProject(project);
        return project;
    }

    public Project updateTitle(Project project, String newTitle, List<Project> projects) throws IOException {
        projectController.updateProject(project,newTitle,project.getProjectStatus(),project.getSupervisor(),projects);
        return project;
    }

    public void viewSupervisorProjects(Supervisor supervisor) {
        for (Project project : supervisor.getProjects()) {
            System.out.println(project.viewDetails());
        }
    }

    public void viewIncomingRequests(User user) {
        for (Request request : user.getRequests()) {
            if (request.getRecipient() instanceof Supervisor) {
                System.out.println(request.viewDetails());
            }
        }
    }
    public void viewRequestHistory(User user) {
        for (Request request : user.getRequests()) {
            if (request.getSender() instanceof Supervisor) {
                System.out.println(request.viewDetails());
            }
        }
    }
    public void approveRequest(Request request, List<Request> requests) throws IOException {
        requestController.approveRequest(request, requests);

    }

    public void rejectRequest(Request request, List<Request> requests) throws IOException {
        requestController.rejectRequest(request, requests);
    }

    public void requestStudentTransferToAnotherSupervisor(Supervisor supervisor, Project project, Supervisor newSupervisor, FYP_Coordinator coordinator,List<Request> requests) throws IOException {
        project.setReplacementSupervisor(newSupervisor);
        Request newRequest = requestController.createRequest(supervisor, coordinator, RequestType.TRANSFER_STUDENT, project,newSupervisor.getUserID(),requests);
        supervisor.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }

}
