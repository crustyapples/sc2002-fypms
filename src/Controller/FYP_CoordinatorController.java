package src.Controller;

import src.Entity.FYP_Coordinator;
import src.Entity.Project;
import src.Entity.Request;
import src.Entity.Student;

import java.util.List;

public class FYP_CoordinatorController extends SupervisorController {
    public void allocateProjectToStudent(FYP_Coordinator coordinator, Student student, Project project) {
        student.setSelectedProject(project);
        project.setStudent(student);
        project.setStatus("allocated");
    }

    public void deallocateProjectFromStudent(FYP_Coordinator coordinator, Student student, Project project) {
        student.setSelectedProject(null);
        project.setStudent(null);
        project.setStatus("available");
    }

    public void manageAllRequests(FYP_Coordinator coordinator) {
        for (Request request : coordinator.getRequests()) {
            System.out.println(request);
            // Implement logic to approve or reject the request
        }
    }

    public List<Project> viewProjectsByFilter(List<Project> projects, String filter) {
        // Implement filtering logic based on the filter parameter
        return null;
    }

    public void generateProjectReport(FYP_Coordinator coordinator, List<Project> projects) {
        // Implement report generation logic based on the projects data
    }
}

