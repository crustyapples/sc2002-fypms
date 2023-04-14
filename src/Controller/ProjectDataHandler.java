package src.Controller;

import src.Entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDataHandler {
    private static final String PROJECT_FILE = "database/Projects_List.txt";

    public List<Project> loadProjectsFromDatabase(List<Supervisor> supervisors, List<Student> students) throws IOException {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROJECT_FILE))) {
            String line;
            int projectID = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                String supervisorName = data[0];
                Supervisor supervisor = findSupervisorByName(supervisors, supervisorName);

                String title = data[1];
                String status = data[2];

                String studentID = data[3];
                studentID = (studentID.equals("NA")) ? null : studentID;
                Student student = findStudentByID(students,studentID);

                String replacementSupervisorName = data[4];
                replacementSupervisorName = (replacementSupervisorName.equals("NA")) ? null : replacementSupervisorName;
                Supervisor replacementSupervisor = findSupervisorByName(supervisors, replacementSupervisorName);

                ProjectStatus projectStatus = getProjectStatusEnum(status);

                if (supervisor != null) {
                    Project project = new Project(projectID, supervisor, replacementSupervisor,student, title, projectStatus);
                    projects.add(project);
                    supervisor.addProject(project);

                    if (student != null) {

                        student.setSelectedProject(project);
                    }

                    if (project.getProjectStatus() == ProjectStatus.ALLOCATED) {
                        supervisor.setNumProjectSupervised(supervisor.getNumProjectsSupervised() + 1);
                    }

                    projectID++;
                }
            }
        }
        return projects;
    }

    public void saveProjectsToDatabase(List<Project> projects) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROJECT_FILE))) {
            for (Project project : projects) {
                writeProjectDetails(project, writer);
            }
        }
    }

    public void saveProjectToDatabase(Project project) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROJECT_FILE, true))) {
            writeProjectDetails(project, writer);
        }
    }

    private void writeProjectDetails(Project project, BufferedWriter writer) throws IOException {
        String supervisorName = project.getSupervisor().getName();
        String title = project.getTitle();
        String status = project.getProjectStatus().toString();
        String studentID = (project.getStudent() == null) ? "NA" : project.getStudent().getUserID();
        String replacementSupervisorName = (project.getReplacementSupervisor() == null) ? "NA" : project.getReplacementSupervisor().getName();
        writer.write(supervisorName + "\t" + title + "\t" + status + "\t" + studentID + "\t" + replacementSupervisorName);
        writer.newLine();
    }


    private Supervisor findSupervisorByName(List<Supervisor> supervisors, String name) {
        for (Supervisor supervisor : supervisors) {
            if (supervisor.getName().equals(name)) {
                return supervisor;
            }
        }
        return null;
    }

    private Student findStudentByID(List<Student> students, String userID) {
        for (Student student : students) {
            if (student.getUserID().equals(userID)) {
                return student;
            }
        }
        return null;
    }

    private static ProjectStatus getProjectStatusEnum(String text) {

        switch (text) {
            case "Available":
                return ProjectStatus.AVAILABLE;
            case "Reserved":
                return ProjectStatus.RESERVED;
            case "Allocated":
                return ProjectStatus.ALLOCATED;
            case "Unavailable":
                return ProjectStatus.UNAVAILABLE;
            default:
                return null;
        }
    }
}
