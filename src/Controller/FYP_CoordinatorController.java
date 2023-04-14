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
            project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised()+1);
            projectController.updateProject(project,project.getTitle(),project.getProjectStatus(),project.getStudent(),project.getSupervisor(),projects);
            System.out.println("Projects supervised  = " + project.getSupervisor().getNumProjectsSupervised());

            if (project.getSupervisor().getNumProjectsSupervised() == 2) {

                for (Project supervisorProject : project.getSupervisor().getProjects()) {
                    if (supervisorProject.getProjectStatus() != ProjectStatus.ALLOCATED) {
                        projectController.updateProject(supervisorProject, supervisorProject.getTitle(), ProjectStatus.UNAVAILABLE, null, supervisorProject.getSupervisor(), projects);
                    }
                }
            }

            return true;
        }



        return false;
    }

    public void deregisterStudentProject(Student student, Project project,List<Project> projects, List<Student> students) throws IOException {

        student.setSelectedProject(null);
        student.setRegistered(false);
        student.setDeRegistered(true);
        studentController.updateStudent(student,students);

        project.setStudent(null);
        project.setProjectStatus(ProjectStatus.AVAILABLE);
        project.getSupervisor().setNumProjectSupervised(project.getSupervisor().getNumProjectsSupervised()-1);

        if (project.getSupervisor().getNumProjectsSupervised() < 2) {

            for (Project supervisorProject : project.getSupervisor().getProjects()) {
                if (supervisorProject.getProjectStatus() != ProjectStatus.ALLOCATED) {
                    projectController.updateProject(supervisorProject, supervisorProject.getTitle(), ProjectStatus.AVAILABLE,null, supervisorProject.getSupervisor(), projects);
                }
            }
        }

        projectController.updateProject(project,project.getTitle(),project.getProjectStatus(),null,project.getSupervisor(),projects);
    }

    public void unreserveProject(Project project, List<Project> projects) throws IOException {
        projectController.updateProject(project, project.getTitle(), ProjectStatus.AVAILABLE, null,  project.getSupervisor(), projects);
    }

    public List<Project> viewProjectsByFilter(List<Project> projects, int filter) {
        List<Project> filteredProjects = new ArrayList<>();

        for (Project project : projects) {
            if (isProjectMatchingFilter(project, filter)) {
                filteredProjects.add(project);
            }
        }

        return filteredProjects;
    }

    private boolean isProjectMatchingFilter(Project project, int filter) {
        String filterValue = null;
        int filterField = filter;

        switch (filterField) {
            case 1:
                filterValue = String.valueOf(project.getProjectID());
                break;
            case 2:
                filterValue = project.getSupervisor().toString();
                break;
            case 3:
                filterValue = project.getReplacementSupervisor().toString();
                break;
            case 4:
                filterValue = project.getStudent().toString();
                break;
            case 5:
                filterValue = project.getTitle();
                break;
            case 6:
                filterValue = project.getProjectStatus().toString();
                break;
            default:
                // Invalid filter, return false
                return false;
        }

        // Perform case-sensitive filtering
        return filterValue != null && filterValue.equals(filter);
    }

    public void generateProjectReport(FYP_Coordinator coordinator, List<Project> projects) {
        // Implement report generation logic based on the projects data
    }

    public void transferStudentToSupervisor(Project project, Supervisor newSupervisor,List<Project> projects) throws IOException {
        projectController.updateProject(project, project.getTitle(), project.getProjectStatus(), project.getStudent(),newSupervisor, projects);
    }
}

