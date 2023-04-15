package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentController extends UserController implements IStudentController{

    private IRequestController requestController;
    private IProjectController projectController;
    private IStudentDataHandler studentDataHandler;

    public StudentController(IStudentDataHandler studentDataHandler, IProjectController projectController, IRequestController requestController) throws IOException {
        this.projectController = projectController;
        this.studentDataHandler = studentDataHandler;
        this.requestController = requestController;
    }
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

    public void selectProjectForStudent(Student student, Project project, FYP_Coordinator coordinator, List<Request> requests, List<Project> projects) throws IOException {
        project.setProjectStatus(ProjectStatus.RESERVED);
        projectController.updateProject(projects);

        Request newRequest = requestController.createRequest(student, coordinator, RequestType.REGISTER, project, null, requests);
        student.addRequest(newRequest);
        coordinator.addRequest(newRequest);

    }

    public Project viewStudentProject(Student student) {
        return student.getSelectedProject();
    }

    public void requestProjectTitleChange(Student student, String newTitle, List<Request> requests) throws IOException {
        Request newRequest = requestController.createRequest(student, student.getSelectedProject().getSupervisor(), RequestType.CHANGE_TITLE, student.getSelectedProject(), newTitle, requests);
        student.addRequest(newRequest);
        student.getSelectedProject().getSupervisor().addRequest(newRequest);
    }

    public void requestProjectDeregistration(Student student, FYP_Coordinator coordinator, List<Request> requests) throws IOException {
        Request newRequest = requestController.createRequest(student, coordinator, RequestType.DEREGISTER, student.getSelectedProject(), null, requests);
        student.addRequest(newRequest);
        coordinator.addRequest(newRequest);
    }


    public void updateStudent(Student student, List<Student> students) throws IOException {
        studentDataHandler.saveStudentsToDatabase(students);
    }
}
