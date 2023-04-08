package src.Controller;

import src.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    public List<Project> getAvailableProjects(List<Project> projects) {
        List<Project> availableProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getStatus().equals("available")) {
                availableProjects.add(project);
            }
        }
        return availableProjects;
    }

    public void selectProjectForStudent(Student student, Project project) {
        student.setSelectedProject(project);
        project.setStatus("reserved");
    }

    public Project viewStudentProject(Student student) {
        return student.getSelectedProject();
    }

    public void requestProjectTitleChange(Student student, String newTitle) {
        Request request = new Request(student, student.getSelectedProject().getSupervisor(), "title_change", student.getSelectedProject(),"pending");
        request.setNewTitle(newTitle);
        student.addRequest(request);
        student.getSelectedProject().getSupervisor().addRequest(request);
    }

    public void requestProjectDeregistration(Student student, FYP_Coordinator coordinator) {
        Request request = new Request(student, coordinator, "de-registration", student.getSelectedProject(), "pending");
        student.addRequest(request);
        coordinator.addRequest(request);
    }

    public void viewStudentRequestHistory(Student student) {
        for (Request request : student.getRequests()) {
            System.out.println(request.viewDetails());
        }
    }
}
