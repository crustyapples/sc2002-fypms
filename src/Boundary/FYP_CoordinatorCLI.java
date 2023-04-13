package src.Boundary;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import src.Controller.FYP_CoordinatorController;
import src.Controller.SupervisorController;
import src.Controller.UserController;
import src.Entity.*;

public class FYP_CoordinatorCLI {
    public Scanner scanner;
    private FYP_CoordinatorController fypCoordinatorController;
    private FYP_Coordinator fypCoordinator;

    public FYP_CoordinatorCLI(FYP_CoordinatorController fypCoordinatorController, FYP_Coordinator fypCoordinator) {
        //super(fypCoordinatorController, fypCoordinator);
        this.fypCoordinatorController = fypCoordinatorController;
        this.fypCoordinator = fypCoordinator;
        scanner = new Scanner(System.in);
    }

    public void manageRequestCLI(User user, List<Request> requests, List<Project> projects, Integer requestChoice, SupervisorController supervisorController) throws IOException {
        for (Request request : user.getRequests()) {
            if (request.getRequestID() == requestChoice) {
                System.out.println(request.viewDetails());
                System.out.println("Do you want to \n1. Approve OR\n2. Reject");
                Integer manageChoice = scanner.nextInt();
                if (manageChoice == 1) {
                    fypCoordinatorController.approveRequest(request,requests);

                    if (request.getType() == RequestType.REGISTER.toString()) {
                        System.out.println("Registering...");
                        if (fypCoordinatorController.allocateProjectToStudent((Student) request.getSender(),request.getProject(), projects)){
                            System.out.println("Successfully Registered!");
                        } else {
                            System.out.println("Supervisor cap is reached!");
                        }
                    }

                    else if (request.getType() == RequestType.DEREGISTER.toString()) {
                        System.out.println("Deregistering...");
                        fypCoordinatorController.deallocateProjectFromStudent((Student) request.getSender(),request.getProject(), projects);
                        System.out.println("Successfully Deregistered!");
                    }

                    else if (request.getType() == RequestType.TRANSFER_STUDENT.toString()) {
                        System.out.println("Transferring...");
                        fypCoordinatorController.transferStudentToSupervisor(request.getProject(),request.getProject().getReplacementSupervisor(),projects);
                    }

                } else {
                    fypCoordinatorController.rejectRequest(request,requests);
                }
            }
        }
    }

    public void displayFYP_CoordinatorMenu() {
        System.out.println("Welcome, " + fypCoordinator.getName() + "!");
        System.out.println("1. Change password");
        System.out.println("2. Create a new project");
        System.out.println("3. Update an existing project");
        System.out.println("4. View your projects");
        System.out.println("5. Manage incoming requests");
        System.out.println("6. View request history");
        System.out.println("7. Request student transfer");
        //Additional functions
        System.out.println("8. Allocate project to student");
        System.out.println("9. Deallocate project from student");
        System.out.println("10. View projects by filter");
        System.out.println("11. Generate project report");
        System.out.println("0. Exit");
    }


    public void handleSupervisorActions(Supervisor supervisor,  FYP_Coordinator fypCoordinator,List<Supervisor> supervisors,List<Project> projects, List<Request> requests) throws IOException {
        boolean exit = false;
        while (!exit) {
            displayFYP_CoordinatorMenu();
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
                    // Call fypCoordinatorController.createNewProject() with the project title
                    System.out.println("Enter the Project Title: ");
                    String title = scanner.nextLine();
                    fypCoordinatorController.createProject(supervisor, title, projects);
                    break;
                case 3:
                    // Call fypCoordinatorController.updateExistingProject() with the new title

                    fypCoordinatorController.viewSupervisorProjects(supervisor);

                    System.out.println("Enter the project ID of the project you wish to update: ");
                    Integer projectChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter the new project Title: ");
                    String newTitle = scanner.nextLine();

                    for (Project project : projects) {
                        if (project.getProjectID() == projectChoice) {
                            fypCoordinatorController.updateTitle(project, newTitle, projects);
                        }
                    }

                    break;
                case 4:
                    // Call fypCoordinatorController.viewSupervisorProjects() and display the result
                    fypCoordinatorController.viewSupervisorProjects(supervisor);
                    break;
                case 5:
                    fypCoordinatorController.viewIncomingRequests(supervisor);
                    System.out.println("Enter the Request ID of the request that you want to handle: ");
                    Integer requestChoice = scanner.nextInt();
                    scanner.nextLine();

                    manageRequestCLI(fypCoordinator, requests, projects, requestChoice, fypCoordinatorController);

                    break;
                case 6:
                    // Call fypCoordinatorController.viewSupervisorRequestHistory() and display the result
                    fypCoordinatorController.viewRequestHistory(supervisor);
                    break;
                case 7:
                    // Call fypCoordinatorController.requestStudentTransferToAnotherSupervisor() with the new supervisor
                    break;
                //Additional functions
                case 8:
                    // Call fypCoordinatorController.allocateProjectToStudent() with the student and project

                    break;
                case 9:
                    // Call fypCoordinatorController.deallocateProjectFromStudent() with the student and project
                    break;
                case 10:
                    // Call fypCoordinatorController.viewProjectsByFilter() with the filter and display the result
                    break;
                case 11:
                    // Call fypCoordinatorController.generateProjectReport() and display the result
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
