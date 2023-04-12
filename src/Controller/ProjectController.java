package src.Controller;

import src.Entity.Project;
import src.Entity.ProjectStatus;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

public class ProjectController {
    private final ProjectDataHandler dataHandler;

    public ProjectController() throws IOException {
        this.dataHandler = new ProjectDataHandler();
    }

    public Project createProject(String title,List<Project> projects, Supervisor supervisor) throws IOException {
        int projectId = projects.size() + 1;
        Project project = new Project(projectId, supervisor, null, title, ProjectStatus.AVAILABLE.toString());
        projects.add(project);
        dataHandler.saveProjectToDatabase(project);
        return project;
    }

    public void updateProject(Project project, String title, String projectStatus, Supervisor supervisor, List<Project> projects) throws IOException {
        project.setTitle(title);
        project.setProjectStatus(projectStatus);
        project.setSupervisor(supervisor);
        dataHandler.saveProjectsToDatabase(projects);
    }


}
