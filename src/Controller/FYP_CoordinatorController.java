package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.List;

public class FYP_CoordinatorController extends SupervisorController {
    public FYP_CoordinatorController() throws IOException {
    }

    public void viewIncomingRequests(User user) {
        for (Request request : user.getRequests()) {
            if (request.getRecipient() instanceof FYP_Coordinator) {
                System.out.println(request.viewDetails());
            }
        }
    }
    public void viewRequestHistory(User user) {
        for (Request request : user.getRequests()) {
            if (request.getSender() instanceof FYP_Coordinator) {
                System.out.println(request.viewDetails());
            }
        }
    }

    public void allocateProjectToStudent(FYP_Coordinator coordinator, Student student, Project project,List<Project> projects) throws IOException {
        student.setSelectedProject(project);
        project.setStudent(student);
        project.setStatus("allocated");
        projectController.updateProject(project,project.getTitle(),project.getStatus(),project.getSupervisor(),projects);
    }

    public void deallocateProjectFromStudent(FYP_Coordinator coordinator, Student student, Project project) {
        student.setSelectedProject(null);
        project.setStudent(null);
        project.setStatus("available");
    }

    public List<Project> viewProjectsByFilter(List<Project> projects, String filter) {
        // Implement filtering logic based on the filter parameter
        return null;
    }

    public void generateProjectReport(FYP_Coordinator coordinator, List<Project> projects) {
        // Implement report generation logic based on the projects data
    }

    public void transferStudentToSupervisor(Project project, Supervisor newSupervisor,List<Project> projects) throws IOException {
        projectController.updateProject(project, project.getTitle(), project.getStatus(), newSupervisor, projects);
    }
}

