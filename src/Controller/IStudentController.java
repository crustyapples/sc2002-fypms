package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.List;

public interface IStudentController extends IUserController{
    List<Project> getAvailableProjects(List<Project> projects);
    void selectProjectForStudent(Student student, Project project, FYP_Coordinator coordinator, List<Request> requests, List<Project> projects) throws IOException;
    Project viewStudentProject(Student student);
    void requestProjectTitleChange(Student student, String newTitle, List<Request> requests) throws IOException;
    void requestProjectDeregistration(Student student, FYP_Coordinator coordinator, List<Request> requests) throws IOException;
    void viewStudentRequestHistory(Student student);
    void updateStudent(Student student, List<Student> students) throws IOException;
}
