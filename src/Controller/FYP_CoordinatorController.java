package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Fyp coordinator controller.
 */
public class FYP_CoordinatorController extends SupervisorController {
    private IFYP_CoordinatorDataHandler dataHandler;

    /**
     * Instantiates a new Fyp coordinator controller.
     *
     * @param studentController the student controller
     * @param projectController the project controller
     * @param requestController the request controller
     * @throws IOException the io exception
     */
    public FYP_CoordinatorController(IStudentController studentController, IProjectController projectController, IRequestController requestController) throws IOException {
        super(studentController,projectController,requestController);
    }

    /**
     * Get incoming requests
     * @param user the user
     * @return incoming requests for user
     */

    public List<Request> getIncomingRequests(User user) {
        List <Request> incomingRequests = new ArrayList<>();
        for (Request request : user.getRequests()) {
            if (request.getSender() instanceof Student | request.getSender() instanceof Supervisor) {
                if (request.getRecipient() instanceof FYP_Coordinator) {
                    incomingRequests.add(request);
                }

            }
        }
        return incomingRequests;
    }

    /**
     * Get request history
     * @param user the user
     * @return request history
     */
    public List<Request> getRequestHistory(User user) {
        List<Request> requestHistory = new ArrayList<>();
        for (Request request : user.getRequests()) {
            if (request.getSender() instanceof FYP_Coordinator) {
                requestHistory.add(request);
            }
        }
        return requestHistory;
    }

    /**
     * Register student project boolean.
     *
     * @param student  the student
     * @param project  the project
     * @param projects the projects
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean registerStudentProject(Student student, Project project,List<Project> projects) throws IOException {

        if (checkSupervisorAvailability(project.getSupervisor(),projects)) {

            student.setSelectedProject(project);
            student.setRegistered(true);

            project.setStudent(student);
            project.setProjectStatus(ProjectStatus.ALLOCATED);
            projectController.updateProject(projects);

            project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised() + 1);
            closeSupervisorProjectAvailability(project.getSupervisor(),projects);

            return true;
        }



        return false;
    }

    /**
     * Deregister student project.
     *
     * @param student  the student
     * @param project  the project
     * @param projects the projects
     * @param students the students
     * @throws IOException the io exception
     */
    public void deregisterStudentProject(Student student, Project project,List<Project> projects, List<Student> students) throws IOException {

        student.setSelectedProject(null);
        student.setRegistered(false);
        student.setDeRegistered(true);
        studentController.updateStudent(student,students);

        project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised() - 1);
        releaseSupervisorProjectAvailability(project.getSupervisor(), projects);

        project.setStudent(null);
        project.setProjectStatus(ProjectStatus.AVAILABLE);
        projectController.updateProject(projects);
    }

    /**
     * Unreserve project.
     *
     * @param project  the project
     * @param projects the projects
     * @throws IOException the io exception
     */
    public void unreserveProject(Project project, List<Project> projects) throws IOException {
        project.setStudent(null);
        project.setProjectStatus(ProjectStatus.AVAILABLE);
        projectController.updateProject(projects);
    }

    /**
     * Generate project report list.
     *
     * @param projects the projects
     * @param filter   the filter
     * @return the list
     */
    public List<Project> generateProjectReport(List<Project> projects, String filter) {

        List<Project> filteredProjects = new ArrayList<>();
        for (Project project : projects) {
            if (filter == "Available") {
                if (project.getProjectStatus() == ProjectStatus.AVAILABLE) {
                    filteredProjects.add(project);
                }
            } else if (filter == "Allocated") {
                if (project.getProjectStatus() == ProjectStatus.ALLOCATED) {
                    filteredProjects.add(project);
                }
            } else if (filter == "Reserved") {
                if (project.getProjectStatus() == ProjectStatus.RESERVED) {
                    filteredProjects.add(project);
                }
            } else if (filter == "Unavailable") {
                if (project.getProjectStatus() == ProjectStatus.UNAVAILABLE) {
                    filteredProjects.add(project);
                }
            } else {
                String studentID = project.getStudent() != null ? project.getStudent().getUserID() : "";
                String supervisorID = project.getSupervisor().getUserID();

                if (Objects.equals(supervisorID, filter) | Objects.equals(studentID, filter)) {
                    filteredProjects.add(project);
                }
            }
        }

        return filteredProjects;

    }

    /**
     * Transfer student to supervisor.
     *
     * @param project  the project
     * @param projects the projects
     * @throws IOException the io exception
     */
    public void transferStudentToSupervisor(Project project, List<Project> projects) throws IOException {

        project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised() - 1);
        releaseSupervisorProjectAvailability(project.getSupervisor(), projects);

        project.getReplacementSupervisor().setNumProjectSupervised(project.getReplacementSupervisor().getNumProjectsSupervised()+1);
        closeSupervisorProjectAvailability(project.getReplacementSupervisor(),projects);

        project.setSupervisor(project.getReplacementSupervisor());
        projectController.updateProject(projects);
    }
    /**
     * Release supervisor project availability
     *
     * @param supervisor  the supervisor
     * @param projects the projects
     * @throws IOException the io exception
     */

    private void releaseSupervisorProjectAvailability(Supervisor supervisor, List<Project> projects) throws IOException {

        if (supervisor.getNumProjectsSupervised() < 2) {

            for (Project supervisorProject : supervisor.getProjects()) {
                if (supervisorProject.getProjectStatus() != ProjectStatus.ALLOCATED) {
                    supervisorProject.setProjectStatus(ProjectStatus.AVAILABLE);
                    projectController.updateProject(projects);
                }
            }
        }
    }
    /**
     * Close supervisor project availability
     *
     * @param supervisor  the supervisor
     * @param projects the projects
     * @throws IOException the io exception
     */
    private void closeSupervisorProjectAvailability(Supervisor supervisor, List<Project> projects) throws IOException {

        if (supervisor.getNumProjectsSupervised() == 2) {
            for (Project supervisorProject : supervisor.getProjects()) {
                if (supervisorProject.getProjectStatus() != ProjectStatus.ALLOCATED) {
                    supervisorProject.setProjectStatus(ProjectStatus.UNAVAILABLE);
                    projectController.updateProject(projects);
                }
            }
        }
    }
}

