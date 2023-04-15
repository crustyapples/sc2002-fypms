package src.Controller;

import src.Entity.Project;
import src.Entity.ProjectStatus;
import src.Entity.Student;
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
        Project project = new Project(projectId, supervisor, null, null, title, ProjectStatus.AVAILABLE);
        projects.add(project);
        dataHandler.saveProjectToDatabase(project);
        return project;
    }

    public void updateProject(List<Project> projects) throws IOException {
        dataHandler.saveProjectsToDatabase(projects);
    }


}
