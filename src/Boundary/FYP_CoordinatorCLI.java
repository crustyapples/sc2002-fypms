package src.Boundary;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import src.ConsoleColors;
import src.Controller.FYP_CoordinatorController;
import src.Controller.SupervisorController;
import src.Controller.UserController;
import src.CustomExceptions.InvalidInputException;
import src.Entity.*;

public class FYP_CoordinatorCLI {
    public Scanner scanner;
    private FYP_CoordinatorController fypCoordinatorController;
    private FYP_Coordinator fypCoordinator;

    private int manageRequestCheck = -1;
    private LoginCLI loginCLI;
    public FYP_CoordinatorCLI(FYP_CoordinatorController fypCoordinatorController, FYP_Coordinator fypCoordinator, LoginCLI loginCLI) {
        //super(fypCoordinatorController, fypCoordinator);
        this.fypCoordinatorController = fypCoordinatorController;
        this.fypCoordinator = fypCoordinator;
        this.loginCLI = loginCLI;
        scanner = new Scanner(System.in);
    }

    public void manageRequestCLI(User user, List<Request> requests, List<Project> projects, List<Student> students, String requestChoice) throws IOException {
        for (Request request : fypCoordinatorController.getIncomingRequests(fypCoordinator)) {
            if (Objects.equals(request.getSender().getUserID(), requestChoice)) {
                manageRequestCheck = 1;
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
                                System.out.println(ConsoleColors.GREEN_BRIGHT + "Successfully Registered!" + ConsoleColors.RESET);
                            } else {
                                System.out.println(ConsoleColors.RED_BRIGHT + "Supervisor cap is reached!"+ ConsoleColors.RESET);
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "Request has been rejected. Project will now be reserved.\n"+ ConsoleColors.RESET);
                            fypCoordinatorController.rejectRequest(request, requests);
                            fypCoordinatorController.unreserveProject(request.getProject(), projects);
                        }
                        break;
                    case DEREGISTER:
                        if (manageChoice == 1) {
                            fypCoordinatorController.approveRequest(request, requests);
                            System.out.println("Deregistering...");
                            fypCoordinatorController.deregisterStudentProject((Student) request.getSender(), request.getProject(), projects, students);
                            System.out.println(ConsoleColors.GREEN_BRIGHT + "Successfully Deregistered!\n"+ ConsoleColors.RESET);
                        } else {
                            fypCoordinatorController.rejectRequest(request, requests);
                        }
                        break;
                    case TRANSFER_STUDENT:
                        if (manageChoice == 1) {
                            fypCoordinatorController.approveRequest(request, requests);
                            System.out.println("Transferring...\n");
                            System.out.println(ConsoleColors.GREEN_BRIGHT + "Transfer successful!\n"+ ConsoleColors.RESET);
                            fypCoordinatorController.transferStudentToSupervisor(request.getProject(), projects);
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "Request to transfer has been rejected.\n"+ ConsoleColors.RESET);
                            fypCoordinatorController.rejectRequest(request, requests);
                        }
                        break;

                    case CHANGE_TITLE:
                        if (manageChoice == 1) {
                            fypCoordinatorController.approveRequest(request, requests);
                            fypCoordinatorController.updateTitle(request.getProject(),request.getBody(),projects);
                            System.out.println(ConsoleColors.GREEN_BRIGHT + "Project title has been updated!"+ ConsoleColors.RESET);
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "Request to change project title has been rejected"+ ConsoleColors.RESET);
                            fypCoordinatorController.rejectRequest(request, requests);
                        }
                    default:
                        fypCoordinatorController.rejectRequest(request, requests);
                }
                return;
            }
        }
        System.out.println(ConsoleColors.RED_BRIGHT + "There is no such request with the given UserID! Try again!\n" + ConsoleColors.RESET);
    }


    public void displayMenu() {

        System.out.println("1. Change password");
        System.out.println("2. Create a new project");
        System.out.println("3. Update an existing project");
        System.out.println("4. View your projects");

        int pendingRequests = 0;
        if (fypCoordinatorController.getIncomingRequests(fypCoordinator).size() > 0) {
            for (Request request : fypCoordinatorController.getIncomingRequests(fypCoordinator)) {
                if (request.getStatus() == RequestStatus.PENDING) {
                    pendingRequests++;
                }
            }
        }

        if (pendingRequests > 0) {
            System.out.println("5. Manage incoming requests" + ConsoleColors.GREEN_BOLD + " New!" + ConsoleColors.RESET);
        }
        else {
            System.out.println("5. Manage incoming requests");
        }

        System.out.println("6. View outgoing request history");
        System.out.println("7. Request student transfer");
        //Additional functions
        System.out.println("8. Generate project report");
        System.out.println("9. View all requests");
        System.out.println("0. Exit");
    }


    public void handleSupervisorActions(Supervisor supervisor,  FYP_Coordinator fypCoordinator,List<Supervisor> supervisors,List<Project> projects, List<Request> requests, List<Student> students) throws IOException {
        boolean exit = false;
        while (!exit) {
            displayMenu();
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
                    loginCLI.authenticateUser();
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

                    Integer projectChoice = -1;
                    while(projectChoice == -1) {
                        try {
                            System.out.println("Enter the project ID of the project you wish to update: ");
                            projectChoice = scanner.nextInt();
                            scanner.nextLine();

                            found: {
                                try {
                                    for (Project project : supervisor.getProjects()) {
                                        if (project.getProjectID() == projectChoice) {
                                            System.out.println("Enter the new project Title: ");
                                            String newTitle = scanner.nextLine();
                                            fypCoordinatorController.updateTitle(project, newTitle, projects);
                                            System.out.println(ConsoleColors.GREEN_BRIGHT + "Title changed" + ConsoleColors.RESET);
                                            break found;
                                        }
                                    }

                                    throw new InvalidInputException.InvalidProjectIDException();

                                } catch (InvalidInputException.InvalidProjectIDException e) {
                                    projectChoice = -1;
                                    System.out.println(e.getMessage());
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please enter a valid integer!\n" + ConsoleColors.RESET);
                            scanner.next(); //Clear the invalid input
                            projectChoice = -1;
                        }
                    }

                    break;
                case 4:
                    // Call fypCoordinatorController.viewSupervisorProjects() and display the result
                    fypCoordinatorController.viewSupervisorProjects(supervisor);
                    break;
                case 5:

                    if (fypCoordinatorController.getIncomingRequests(supervisor).size() == 0) {
                        System.out.println(ConsoleColors.RED_BRIGHT + "You have no incoming requests!" + ConsoleColors.RESET);
                        break;
                    } else {
                        System.out.println("Incoming requests: \n");
                        for (Request request : fypCoordinatorController.getIncomingRequests(supervisor)) {
                            System.out.println(request.viewDetails());
                        }
                    }
                    manageRequestCheck = -1;
                    Integer userFound = -1;
                    while (userFound == -1) {
                        try {
                            while (manageRequestCheck == -1) {

                                System.out.println("Enter the User ID of the request that you want to handle: ");
                                String requestChoice = scanner.nextLine();
                                manageRequestCLI(fypCoordinator, requests, projects, students, requestChoice);
                                userFound = 1;
                            }

                        } catch (InputMismatchException e) {
                            userFound = -1;
                            System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please input an integer!\n" + ConsoleColors.RESET);
                            scanner.next(); //Clear the invalid input
                        }
                    }

                    break;
                case 6:
                    // Call fypCoordinatorController.viewSupervisorRequestHistory() and display the result
                    if (fypCoordinatorController.getRequestHistory(supervisor).size() > 0) {
                        System.out.println("Outgoing requests: \n");
                        for (Request request : fypCoordinatorController.getRequestHistory(supervisor)) {
                            System.out.println(request.viewDetails());
                        }
                    } else {
                        System.out.println(ConsoleColors.RED_BRIGHT + "You have no outgoing requests!" + ConsoleColors.RESET);
                        break;
                    }
                    break;
                case 7:
                    // Call fypCoordinatorController.requestStudentTransferToAnotherSupervisor() with the new supervisor
                    fypCoordinatorController.viewSupervisorProjects(supervisor);

                    if (fypCoordinator.getProjects().size() == 0) {
                        System.out.println("You have no projects!");
                        break;
                    }
                    projectChoice = -1;
                    SupervisorCLI.transferStudentCLI(supervisor, fypCoordinator, supervisors, projects, requests, projectChoice, scanner, fypCoordinatorController);

                    break;
                case 8:
                    // Call fypCoordinatorController.viewProjectsByFilter() with the filter and display the result
                    try {
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

/*                                int statusChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (statusChoice == 1) {
                                    filterChoice = "Available";
                                } else if (statusChoice == 2) {
                                    filterChoice = "Allocated";
                                } else if (statusChoice == 3) {
                                    filterChoice = "Reserved";
                                } else if (statusChoice == 4) {
                                    filterChoice = "Unavailable";
                                }*/
                        }

                        if (filter == 1) {
                            for (Supervisor correctSupervisor : supervisors) {
                                if (correctSupervisor.getUserID().equals(filterChoice)) {
                                    List<Project> filteredProjects = fypCoordinatorController.generateProjectReport(projects, filterChoice);
                                    System.out.println("Filtered Projects: \n");
                                    for (Project project : filteredProjects) {

                                        System.out.println(project.viewDetails());
                                    }
                                } else {
                                    System.out.println(ConsoleColors.RED_BRIGHT + "No such supervisor!" + ConsoleColors.RESET);
                                    break;
                                }
                            }
                        } else if (filter == 2) {
                            for (Student correctStudent : students) {
                                if (correctStudent.getUserID().equals(filterChoice)) {
                                    List<Project> filteredProjects = fypCoordinatorController.generateProjectReport(projects, filterChoice);
                                    System.out.println("Filtered Projects: \n");
                                    for (Project project : filteredProjects) {

                                        System.out.println(project.viewDetails());
                                    }
                                } else {
                                    System.out.println(ConsoleColors.RED_BRIGHT + "No such student!" + ConsoleColors.RESET);
                                    break;
                                }
                            }
                        } else if (filter == 3) {
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

                            if (filterChoice == "Available" || filterChoice == "Allocated" || filterChoice == "Reserved" || filterChoice == "Unavailable") {

                                List<Project> filteredProjects = fypCoordinatorController.generateProjectReport(projects, filterChoice);


                                System.out.println("Filtered Projects: \n");
                                for (Project project : filteredProjects) {

                                    System.out.println(project.viewDetails());
                                }
                            } else {
                                System.out.println(ConsoleColors.RED_BRIGHT + "No such option!" + ConsoleColors.RESET);
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please input an integer!\n"+ ConsoleColors.RESET);
                        scanner.next(); //Clear the invalid input
                    }
                    break;
                case 9:

                    System.out.println("All Requests: \n");
                    for (Request request:requests) {
                        System.out.println(request.viewDetails());
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