package src.Controller;

import src.Entity.Project;
import src.Entity.ProjectStatus;
import src.Entity.Student;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

/**
 * The type Project controller.
 */
public class ProjectController implements IProjectController{
    private IProjectDataHandler dataHandler;

    /**
     * Instantiates a new Project controller.
     *
     * @param projectDataHandler the project data handler
     * @throws IOException the io exception
     */
    public ProjectController(IProjectDataHandler projectDataHandler) throws IOException {
        this.dataHandler = projectDataHandler;
    }
    /**
     * Create a project.
     *
     * @param title the title of project
     * @param projects the list of projects
     * @param supervisor the supervisor
     * @return project the project
     * @throws IOException the io exception
     */
    public Project createProject(String title,List<Project> projects, Supervisor supervisor) throws IOException {
        int projectId = projects.size() + 1;
        Project project = new Project(projectId, supervisor, null, null, title, ProjectStatus.AVAILABLE);
        projects.add(project);
        dataHandler.saveProjectToDatabase(project);
        return project;
    }
    /**
     * Update projects
     *
     * @param projects the projects
     * @throws IOException the io exception
     */
    public void updateProject(List<Project> projects) throws IOException {
        dataHandler.saveProjectsToDatabase(projects);
    }


}
