package src.Boundary;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import src.Controller.SupervisorController;
import src.Controller.UserController;
import src.Entity.FYP_Coordinator;
import src.Entity.Project;
import src.Entity.Request;
import src.Entity.User;
import src.Entity.Supervisor;

public class SupervisorCLI {
    public Scanner scanner;
    private SupervisorController supervisorController;
    private Supervisor supervisor;

    public SupervisorCLI(SupervisorController supervisorController, Supervisor supervisor) {
        this.supervisorController = supervisorController;
        this.supervisor = supervisor;
        scanner = new Scanner(System.in);
    }

    public void manageRequestCLI(User user, List<Request> requests, List<Project> projects, Integer requestChoice, SupervisorController supervisorController) throws IOException {
        for (Request request : user.getRequests()) {
            if (request.getRequestID() == requestChoice) {
                System.out.println(request.viewDetails());
                System.out.println("Do you want to \n1. Approve OR\n2. Reject");
                Integer manageChoice = scanner.nextInt();
                if (manageChoice == 1) {
                    supervisorController.approveRequest(request,requests);
                    supervisorController.updateTitle(request.getProject(),request.getBody(),projects);
                } else {
                    supervisorController.rejectRequest(request,requests);
                }
            }
        }
    }

    public void displaySupervisorMenu() {
        System.out.println("Welcome, " + supervisor.getName() + "!");
        System.out.println("1. Change password");
        System.out.println("2. Create a new project");
        System.out.println("3. Update an existing project");
        System.out.println("4. View your projects");
        System.out.println("5. Manage incoming requests");
        System.out.println("6. View request history");
        System.out.println("7. Request student transfer");
        System.out.println("0. Exit");
    }

    public void handleSupervisorActions(Supervisor supervisor, FYP_Coordinator coordinator, List<Supervisor> supervisors, List<Project> projects, List<Request> requests) throws IOException {
        boolean exit = false;
        while (!exit) {
            displaySupervisorMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    //System.out.println("Change Password: ");
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    supervisor.changePassword(newPassword);

                    String userID = supervisor.getUserID();
                    UserController userController = new UserController();
                    //How to get the userPasswords?
                    //userController.changeUserPassword(userPasswords, userID, newPassword);
                    System.out.println("You will now be logged out. Please login again!");
                    exit = true;
                    break;
                case 2:
                    // Call supervisorController.createNewProject() with the project title
                    System.out.println("Enter the Project Title: ");
                    String title = scanner.nextLine();
                    supervisorController.createProject(supervisor, title, projects);
                    break;
                case 3:
                    // Call supervisorController.updateExistingProject() with the new title

                    supervisorController.viewSupervisorProjects(supervisor);

                    System.out.println("Enter the project ID of the project you wish to update: ");
                    Integer projectChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter the new project Title: ");
                    String newTitle = scanner.nextLine();

                    for (Project project : projects) {
                        if (project.getProjectID() == projectChoice) {
                            supervisorController.updateTitle(project, newTitle, projects);
                        }
                    }

                    break;
                case 4:
                    // Call supervisorController.viewSupervisorProjects() and display the result
                    supervisorController.viewSupervisorProjects(supervisor);
                    break;
                case 5:
                    // Call supervisorController.manageStudentRequests()
                    supervisorController.viewIncomingRequests(supervisor);
                    System.out.println("Enter the Request ID of the request that you want to handle: ");
                    Integer requestChoice = scanner.nextInt();
                    scanner.nextLine();
                    manageRequestCLI(supervisor, requests, projects, requestChoice, supervisorController);

                    break;
                case 6:
                    // Call supervisorController.viewSupervisorRequestHistory() and display the result
                    supervisorController.viewRequestHistory(supervisor);
                    break;
                case 7:
                    // Call supervisorController.requestStudentTransferToAnotherSupervisor() with the new supervisor
                    supervisorController.viewSupervisorProjects(supervisor);

                    System.out.println("Enter the project ID of the project you wish to transfer: ");
                    projectChoice = scanner.nextInt();
                    scanner.nextLine();

                    for (Project project : projects) {
                        if (project.getProjectID() == projectChoice) {
                            System.out.println(project.viewDetails());
                            System.out.println("Enter the supervisorID of your replacement: ");
                            String replacementSupervisorID = scanner.nextLine();

                            for (Supervisor replacementSupervisor : supervisors) {
                                if (Objects.equals(replacementSupervisor.getUserID(), replacementSupervisorID)) {
                                    System.out.println("Requesting...");
                                    supervisorController.requestStudentTransferToAnotherSupervisor(supervisor, project, replacementSupervisor, coordinator, requests);
                                }
                            }
                        }
                    }

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
