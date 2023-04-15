package src.Controller;

import src.Entity.Project;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

public interface IProjectController {
    Project createProject(String title,List<Project> projects, Supervisor supervisor) throws IOException;
    void updateProject(List<Project> projects) throws IOException;
}

