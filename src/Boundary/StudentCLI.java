package src.Boundary;

import java.util.List;
import java.util.Scanner;

import src.Controller.StudentController;
import src.Entity.FYP_Coordinator;
import src.Entity.Project;
import src.Entity.Student;

public class StudentCLI {
    private Scanner scanner;
    private StudentController studentController;

    public StudentCLI(StudentController studentController) {
        this.studentController = studentController;
        scanner = new Scanner(System.in);
    }

    public void displayStudentMenu() {
        System.out.println("1. View available projects");
        System.out.println("2. Select a project");
        System.out.println("3. View your project");
        System.out.println("4. Request project title change");
        System.out.println("5. Request project deregistration");
        System.out.println("6. View request history");
        System.out.println("0. Exit");
    }

    public void handleStudentActions(Student student, List<Project> projects, FYP_Coordinator coordinator) {
        boolean exit = false;
        while (!exit) {
            displayStudentMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Call studentController.getAvailableProjects() and display the result
                    System.out.println("Available Projects: ");
                    List<Project> availableProjects = studentController.getAvailableProjects(projects);
                    int projectId = 1;
                    for (Project availableProject : availableProjects) {
                        System.out.println("ID: " + projectId + " Title: " + availableProject.getTitle() + " Supervisor: " + availableProject.getSupervisor().getName() + " Status: " + availableProject.getStatus());
                        projectId++;
                    }
                    break;
                case 2:
                    // Call studentController.selectProjectForStudent() with the selected project
                    System.out.println("Enter the ProjectID:");
                    int projectChoice = scanner.nextInt();
                    availableProjects = studentController.getAvailableProjects(projects);
                    Project selectedProject = availableProjects.get(projectChoice - 1);
                    studentController.selectProjectForStudent(student,selectedProject);

                    break;
                case 3:
                    // Call studentController.isHashProject() and display the result
                    if (!student.isHasProject()) {
                        System.out.println("You have not registered a project!");
                    } else {
                        System.out.println("Your Project: "+ student.getSelectedProject().getTitle());
                    }
                    break;
                case 4:
                    // Call studentController.requestProjectTitleChange() with the new title
                    System.out.println("Enter new project title:");
                    String newTitle = scanner.nextLine();
                    studentController.requestProjectTitleChange(student, newTitle);

                    break;
                case 5:
                    // Call studentController.requestProjectDeregistration()
                    studentController.requestProjectDeregistration(student,coordinator);
                    break;
                case 6:
                    // Call studentController.viewStudentRequestHistory() and display the result
                    studentController.viewStudentRequestHistory(student);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
