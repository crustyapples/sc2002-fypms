package src.Boundary;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import src.Controller.SupervisorController;
import src.Entity.Project;
import src.Entity.Supervisor;

public class SupervisorCLI {
    public Scanner scanner;
    private SupervisorController supervisorController;

    public SupervisorCLI(SupervisorController supervisorController) {
        this.supervisorController = supervisorController;
        scanner = new Scanner(System.in);
    }

    public void displaySupervisorMenu() {
        System.out.println("1. Create a new project");
        System.out.println("2. Update an existing project");
        System.out.println("3. View your projects");
        System.out.println("4. Manage student requests");
        System.out.println("5. View request history");
        System.out.println("6. Request student transfer");
        System.out.println("0. Exit");
    }

    public void handleSupervisorActions(Supervisor supervisor, List<Project> projects) throws IOException {
        boolean exit = false;
        while (!exit) {
            displaySupervisorMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Call supervisorController.createNewProject() with the project title
                    System.out.println("Enter the Project Title: ");
                    String title = scanner.nextLine();
                    supervisorController.createNewProject(supervisor, title, projects);
                    break;
                case 2:
                    // Call supervisorController.updateExistingProject() with the new title

                    supervisorController.viewSupervisorProjects(supervisor);

                    System.out.println("Enter the project ID of the project you wish to update: ");
                    Integer projectChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter the new project Title: ");
                    String newTitle = scanner.nextLine();

                    for (Project project : projects) {
                        if (project.getProjectID() == projectChoice) {
                            supervisorController.updateExistingProject(project, newTitle, projects);
                        }
                    }


                    break;
                case 3:
                    // Call supervisorController.viewSupervisorProjects() and display the result
                    supervisorController.viewSupervisorProjects(supervisor);
                    break;
                case 4:
                    // Call supervisorController.manageStudentRequests()
                    break;
                case 5:
                    // Call supervisorController.viewSupervisorRequestHistory() and display the result
                    break;
                case 6:
                    // Call supervisorController.requestStudentTransferToAnotherSupervisor() with the new supervisor
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
