package src.Controller;

import src.Entity.Project;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

/**
 * The interface Project controller.
 */
public interface IProjectController {
    /**
     * Create project project.
     *
     * @param title      the title
     * @param projects   the projects
     * @param supervisor the supervisor
     * @return the project
     * @throws IOException the io exception
     */
    Project createProject(String title,List<Project> projects, Supervisor supervisor) throws IOException;

    /**
     * Update project.
     *
     * @param projects the projects
     * @throws IOException the io exception
     */
    void updateProject(List<Project> projects) throws IOException;
}

