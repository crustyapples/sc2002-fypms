package src.Controller;

import src.Entity.*;

import java.io.*;
import java.util.List;

public interface IProjectDataHandler {
    List<Project> loadProjectsFromDatabase(List<User> users) throws IOException;

    void saveProjectsToDatabase(List<Project> projects) throws IOException;

    void saveProjectToDatabase(Project project) throws IOException;
}
