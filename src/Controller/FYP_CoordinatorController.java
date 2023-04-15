package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
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

    public boolean registerStudentProject(Student student, Project project,List<Project> projects) throws IOException {

        if (checkSupervisorAvailability(project.getSupervisor(),projects)) {

            student.setSelectedProject(project);
            student.setRegistered(true);

            project.setStudent(student);
            project.setProjectStatus(ProjectStatus.ALLOCATED);
            projectController.updateProject(projects);

            project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised() + 1);
            closeSupervisorProjectAvailability(project.getSupervisor(),projects);

            System.out.println("Projects supervised  = " + project.getSupervisor().getNumProjectsSupervised());
            return true;
        }



        return false;
    }

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

    public void unreserveProject(Project project, List<Project> projects) throws IOException {
        project.setStudent(null);
        project.setProjectStatus(ProjectStatus.AVAILABLE);
        projectController.updateProject(projects);
    }

    public List<Project> viewProjectsByFilter(List<Project> projects, int filter) {
        // implement project filtering
        List<Project> filteredProjects = projects;
        return projects;
    }

    public void generateProjectReport(FYP_Coordinator coordinator, List<Project> projects) {
        // Implement report generation logic based on the projects data
    }

    public void transferStudentToSupervisor(Project project, List<Project> projects) throws IOException {

        project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised() - 1);
        releaseSupervisorProjectAvailability(project.getSupervisor(), projects);

        project.getReplacementSupervisor().setNumProjectSupervised(project.getReplacementSupervisor().getNumProjectsSupervised()+1);
        closeSupervisorProjectAvailability(project.getReplacementSupervisor(),projects);

        project.setSupervisor(project.getReplacementSupervisor());
        projectController.updateProject(projects);
    }

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

