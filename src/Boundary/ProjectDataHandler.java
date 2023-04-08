package src.Boundary;

import src.Entity.Project;
import src.Entity.Supervisor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDataHandler {
    private static final String PROJECT_FILE = "database/Projects_List.txt";

    public List<Project> loadProjectsFromDatabase(List<Supervisor> supervisors) throws IOException {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROJECT_FILE))) {
            String line;
            int projectID = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                String supervisorName = data[0];
                String title = data[1];

                Supervisor supervisor = findSupervisorByName(supervisors, supervisorName);
                if (supervisor != null) {
                    Project project = new Project(supervisor, null, title, "available");
                    projects.add(project);
                    projectID++;
                }
            }
        }
        return projects;
    }

    public void saveProjectsToDatabase(List<Project> projects) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROJECT_FILE))) {
            for (Project project : projects) {
                String supervisorEmail = project.getSupervisor().getEmail();
                String title = project.getTitle();
                writer.write(supervisorEmail + "\t" + title);
                writer.newLine();
            }
        }
    }

    private Supervisor findSupervisorByName(List<Supervisor> supervisors, String name) {
        for (Supervisor supervisor : supervisors) {
            if (supervisor.getName().equals(name)) {
                return supervisor;
            }
        }
        return null;
    }
}
