package src.Controller;
import src.Boundary.ProjectDataHandler;
import src.Entity.FYP_Coordinator;
import src.Entity.Project;
import src.Entity.Request;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

public class SupervisorController {
    private ProjectDataHandler projectDataHandler;

    public SupervisorController() {
        projectDataHandler = new ProjectDataHandler();
    }

    public Project createNewProject(Supervisor supervisor, String title) {
        Project project = new Project(supervisor, null, title, "available");
        supervisor.getProjects().add(project);
        try {
            projectDataHandler.saveProjectsToDatabase(supervisor.getProjects());
        } catch (IOException e) {
            System.out.println("Error saving projects: " + e.getMessage());
        }
        return project;
    }

    public void updateExistingProject(Project project, String newTitle) {
        project.setTitle(newTitle);
        try {
            projectDataHandler.saveProjectsToDatabase(project.getSupervisor().getProjects());
        } catch (IOException e) {
            System.out.println("Error saving projects: " + e.getMessage());
        }
    }

    public void viewSupervisorProjects(Supervisor supervisor) {
        for (Project project : supervisor.getProjects()) {
            System.out.println(project);
        }
    }

    public void manageStudentRequests(Supervisor supervisor) {
        for (Request request : supervisor.getRequests()) {
            System.out.println(request);
            // Implement logic to approve or reject the request
        }
    }

    public void viewSupervisorRequestHistory(Supervisor supervisor) {
        for (Request request : supervisor.getRequests()) {
            System.out.println(request);
        }
    }

    public void requestStudentTransferToAnotherSupervisor(Supervisor supervisor, Project project, Supervisor newSupervisor, FYP_Coordinator coordinator) {
        Request request = new Request(supervisor, coordinator, "transfer_student", project,"pending" );
        request.setNewSupervisor(newSupervisor);
        supervisor.getRequests().add(request);
        coordinator.getRequests().add(request);
    }
}
