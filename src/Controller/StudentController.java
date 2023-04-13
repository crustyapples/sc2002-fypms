package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private RequestController requestController;

    public StudentController() throws IOException {
        requestController = new RequestController();
    }
    public List<Project> getAvailableProjects(List<Project> projects) {
        List<Project> availableProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getProjectStatus() == ProjectStatus.AVAILABLE) {
            if (project.getProjectStatus().equals(ProjectStatus.AVAILABLE.toString())) {
                availableProjects.add(project);
            }
        }
        return availableProjects;
    }

    public void selectProjectForStudent(Student student, Project project, FYP_Coordinator coordinator, List<Request> requests) throws IOException {
        project.setProjectStatus(ProjectStatus.RESERVED.toString());
        Request newRequest = requestController.createRequest(student, coordinator, RequestType.REGISTER.toString(), project, null, requests);
        student.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }

    public Project viewStudentProject(Student student) {
        return student.getSelectedProject();
    }

    public void requestProjectTitleChange(Student student, String newTitle, List<Request> requests) throws IOException {
        Request newRequest = requestController.createRequest(student, student.getSelectedProject().getSupervisor(), RequestType.CHANGE_TITLE.toString(), student.getSelectedProject(), newTitle, requests);
        student.addRequest(newRequest);
        student.getSelectedProject().getSupervisor().addRequest(newRequest);
    }

    public void requestProjectDeregistration(Student student, FYP_Coordinator coordinator, List<Request> requests) throws IOException {
        Request newRequest = requestController.createRequest(student, student.getSelectedProject().getSupervisor(), RequestType.DEREGISTER.toString(), student.getSelectedProject(), null, requests);
        student.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }

    public void viewStudentRequestHistory(Student student) {
        for (Request request : student.getRequests()) {
            System.out.println(request.viewDetails());
        }
    }
}
