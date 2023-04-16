package src.Controller;

import src.Entity.*;

import java.io.*;
import java.util.List;

/**
 * The interface Project data handler.
 */
public interface IProjectDataHandler {
    /**
     * Load projects from database list.
     *
     * @param users the users
     * @return the list
     * @throws IOException the io exception
     */
    List<Project> loadProjectsFromDatabase(List<User> users) throws IOException;

    /**
     * Save projects to database.
     *
     * @param projects the projects
     * @throws IOException the io exception
     */
    void saveProjectsToDatabase(List<Project> projects) throws IOException;

    /**
     * Save project to database.
     *
     * @param project the project
     * @throws IOException the io exception
     */
    void saveProjectToDatabase(Project project) throws IOException;
}
