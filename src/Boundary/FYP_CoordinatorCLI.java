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

    public void manageRequestCLI(User user, List<Request> requests, List<Project> projects, List<Student> students, Integer requestChoice) throws IOException {
        for (Request request : user.getRequests()) {
            if (request.getRequestID() == requestChoice) {
                System.out.println(request.viewDetails());
                System.out.println("Do you want to \n1. Approve OR\n2. Reject");
                Integer manageChoice = scanner.nextInt();

                switch (request.getType()) {
                    case REGISTER:
                        if (manageChoice == 1) {

                            if (fypCoordinatorController.checkSupervisorAvailability(request.getProject().getSupervisor(),projects)) {
                                fypCoordinatorController.approveRequest(request, requests);
                                System.out.println("Registering...");
                                fypCoordinatorController.registerStudentProject((Student) request.getSender(), request.getProject(), projects);
                                System.out.println("Successfully Registered!");
                            } else {
                                System.out.println("Supervisor cap is reached!");
                            }
                        } else {
                            fypCoordinatorController.rejectRequest(request, requests);
                            fypCoordinatorController.unreserveProject(request.getProject(), projects);
                        }
                        break;
                    case DEREGISTER:
                        if (manageChoice == 1) {
                            fypCoordinatorController.approveRequest(request, requests);
                            System.out.println("Deregistering...");
                            fypCoordinatorController.deregisterStudentProject((Student) request.getSender(), request.getProject(), projects, students);
                            System.out.println("Successfully Deregistered!");
                        } else {
                            fypCoordinatorController.rejectRequest(request, requests);
                        }
                        break;
                    case TRANSFER_STUDENT:
                        if (manageChoice == 1) {
                            fypCoordinatorController.approveRequest(request, requests);
                            System.out.println("Transferring...");
                            fypCoordinatorController.transferStudentToSupervisor(request.getProject(), projects);
                        } else {
                            fypCoordinatorController.rejectRequest(request, requests);
                        }
                        break;

                    case CHANGE_TITLE:
                        if (manageChoice == 1) {
                            fypCoordinatorController.approveRequest(request, requests);
                            fypCoordinatorController.updateTitle(request.getProject(),request.getBody(),projects);
                        } else {
                            fypCoordinatorController.rejectRequest(request, requests);
                        }
                    default:
                        fypCoordinatorController.rejectRequest(request, requests);
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
        System.out.println("10. Generate project report");
        System.out.println("0. Exit");
    }


    public void handleSupervisorActions(Supervisor supervisor,  FYP_Coordinator fypCoordinator,List<Supervisor> supervisors,List<Project> projects, List<Request> requests, List<Student> students) throws IOException {
        boolean exit = false;
        while (!exit) {
            displayFYP_CoordinatorMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:

                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    String userID = supervisor.getUserID();
                    UserController userController = new UserController();
                    userController.changeUserPassword(userID, newPassword);
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

                    manageRequestCLI(fypCoordinator, requests, projects, students, requestChoice);

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
                    System.out.println("Enter filter choice: ");
                    System.out.println("1. Supervisor");
                    System.out.println("2. Student");
                    System.out.println("3. Status");

                    int filter = scanner.nextInt();
                    String filterChoice = "";
                    scanner.nextLine();

                    if (filter == 1) {
                        System.out.println("Enter the SupervisorID: ");
                        String supervisorID = scanner.nextLine();
                        filterChoice = supervisorID;
                    } else if (filter == 2) {
                        System.out.println("Enter the StudentID: ");
                        String studentID = scanner.nextLine();
                        filterChoice = studentID;
                    } else if (filter == 3) {
                        System.out.println("Select the Project Status: ");
                        System.out.println("1. Available");
                        System.out.println("2. Allocated");
                        System.out.println("3. Reserved");
                        System.out.println("4. Unavailable");

                        int statusChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (statusChoice == 1) {
                            filterChoice = "Available";
                        } else if (statusChoice == 2) {
                            filterChoice = "Allocated";
                        } else if (statusChoice == 3) {
                            filterChoice = "Reserved";
                        } else if (statusChoice == 4) {
                            filterChoice = "Unavailable";
                        }

                    }

                    List<Project> filteredProjects = fypCoordinatorController.generateProjectReport(projects, filterChoice);

                    for (Project project : filteredProjects) {
                        System.out.println(project.viewDetails());
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
