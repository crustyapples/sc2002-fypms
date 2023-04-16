package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.List;

/**
 * The interface Student controller.
 */
public interface IStudentController extends IUserController{
    /**
     * Gets available projects.
     *
     * @param projects the projects
     * @return the available projects
     */
    List<Project> getAvailableProjects(List<Project> projects);

    /**
     * Select project for student.
     *
     * @param student     the student
     * @param project     the project
     * @param coordinator the coordinator
     * @param requests    the requests
     * @param projects    the projects
     * @throws IOException the io exception
     */
    void selectProjectForStudent(Student student, Project project, FYP_Coordinator coordinator, List<Request> requests, List<Project> projects) throws IOException;

    /**
     * View student project project.
     *
     * @param student the student
     * @return the project
     */
    Project viewStudentProject(Student student);

    /**
     * Request project title change.
     *
     * @param student  the student
     * @param newTitle the new title
     * @param requests the requests
     * @throws IOException the io exception
     */
    void requestProjectTitleChange(Student student, String newTitle, List<Request> requests) throws IOException;

    /**
     * Request project deregistration.
     *
     * @param student     the student
     * @param coordinator the coordinator
     * @param requests    the requests
     * @throws IOException the io exception
     */
    void requestProjectDeregistration(Student student, FYP_Coordinator coordinator, List<Request> requests) throws IOException;

    /**
     * Update student.
     *
     * @param student  the student
     * @param students the students
     * @throws IOException the io exception
     */
    void updateStudent(Student student, List<Student> students) throws IOException;
}
