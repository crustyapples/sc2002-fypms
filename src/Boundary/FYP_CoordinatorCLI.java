package src.Boundary;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import src.ConsoleColors;
import src.Controller.FYP_CoordinatorController;
import src.CustomExceptions.InvalidInputException;
import src.Entity.*;

/**
 * The type Fyp coordinator cli.
 */
public class FYP_CoordinatorCLI extends SupervisorCLI{
    /**
     * The Scanner.
     */
    public Scanner scanner;
    private FYP_CoordinatorController fypCoordinatorController;
    private FYP_Coordinator fypCoordinator;

    private int manageRequestCheck = -1;
    private LoginCLI loginCLI;
    private PasswordChangerCLI passwordChangerCLI;
    private ProjectUpdaterCLI projectUpdaterCLI;

    /**
     * Instantiates a new Fyp coordinator cli.
     *
     * @param fypCoordinatorController the fyp coordinator controller
     * @param fypCoordinator           the fyp coordinator
     * @param loginCLI                 the login cli
     * @param passwordChangerCLI       the password changer cli
     * @param projectUpdaterCLI        the project updater cli
     */
    public FYP_CoordinatorCLI(FYP_CoordinatorController fypCoordinatorController, FYP_Coordinator fypCoordinator, LoginCLI loginCLI,PasswordChangerCLI passwordChangerCLI, ProjectUpdaterCLI projectUpdaterCLI) {
        super(fypCoordinatorController, fypCoordinator, loginCLI, passwordChangerCLI, projectUpdaterCLI);
        this.fypCoordinatorController = fypCoordinatorController;
        this.fypCoordinator = fypCoordinator;
        this.loginCLI = loginCLI;
        this.passwordChangerCLI = passwordChangerCLI;
        this.projectUpdaterCLI = projectUpdaterCLI;
        scanner = new Scanner(System.in);
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

        System.out.println("6. View incoming request history");
        System.out.println("7. View outgoing request history");
        System.out.println("8. Request student transfer");
        //Additional functions
        System.out.println("9. Generate project report");
        System.out.println("10. View all requests");
        System.out.println("0. Exit");
    }


    /**
     * Handle supervisor actions.
     *
     * @param supervisor     the supervisor
     * @param fypCoordinator the fyp coordinator
     * @param supervisors    the supervisors
     * @param projects       the projects
     * @param requests       the requests
     * @param students       the students
     * @throws IOException the io exception
     */
    public void handleSupervisorActions(Supervisor supervisor,  FYP_Coordinator fypCoordinator,List<Supervisor> supervisors,List<Project> projects, List<Request> requests, List<Student> students) throws IOException {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    passwordChangerCLI.changePassword();
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
                    projectUpdaterCLI.updateProjects(projects);

                    break;
                case 4:
                    // Call fypCoordinatorController.viewSupervisorProjects() and display the result
                    if (fypCoordinator.getProjects().size()==0) {
                        System.out.println(ConsoleColors.RED_BRIGHT + "You have no Projects!" + ConsoleColors.RESET);
                    }
                    fypCoordinatorController.viewSupervisorProjects(supervisor);
                    break;
                case 5:

                    manageIncomingRequests(supervisor, fypCoordinator, projects, requests, students);

                    break;

                case 6:
                    displayRequestHistory(fypCoordinatorController.getIncomingRequests(supervisor), "Incoming requests: \n", fypCoordinatorController.getIncomingRequests(supervisor), "You have no incoming requests!");
                    break;

                case 7:
                    // Call fypCoordinatorController.viewSupervisorRequestHistory() and display the result
                    displayRequestHistory(fypCoordinatorController.getRequestHistory(supervisor), "Outgoing requests: \n", fypCoordinatorController.getRequestHistory(supervisor), "You have no outgoing requests!");
                    break;
                case 8:
                    // Call fypCoordinatorController.requestStudentTransferToAnotherSupervisor() with the new supervisor
                    fypCoordinatorController.viewSupervisorProjects(supervisor);

                    if (fypCoordinator.getProjects().size() == 0) {
                        System.out.println("You have no projects!");
                        break;
                    }
                    int projectChoice = -1;
                    SupervisorCLI.transferStudentCLI(supervisor, fypCoordinator, supervisors, projects, requests, projectChoice, scanner, fypCoordinatorController);

                    break;
                case 9:
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
                            int supervisorFound = 0;
                            for (Supervisor correctSupervisor : supervisors) {
                                if (correctSupervisor.getUserID().equals(filterChoice)) {
                                    supervisorFound = 1;
                                    List<Project> filteredProjects = fypCoordinatorController.generateProjectReport(projects, filterChoice);
                                    System.out.println("Filtered Projects: \n");
                                    for (Project project : filteredProjects) {
                                        System.out.println(project.viewDetails());
                                    }
                                    break;
                                }
                            }
                            if (supervisorFound == 0) {
                                System.out.println(ConsoleColors.RED_BRIGHT + "No such supervisor!" + ConsoleColors.RESET);
                                break;
                            }

                            break;
                        } else if (filter == 2) {
                            int studentFound = 0;
                            for (Student correctStudent : students) {
                                if (correctStudent.getUserID().equals(filterChoice)) {
                                    studentFound = 1;
                                    List<Project> filteredProjects = fypCoordinatorController.generateProjectReport(projects, filterChoice);
                                    System.out.println("Filtered Projects: \n");
                                    for (Project project : filteredProjects) {
                                        System.out.println(project.viewDetails());
                                    }
                                    break;
                                }
                            }
                            if (studentFound == 0) {
                                System.out.println(ConsoleColors.RED_BRIGHT + "No such student!" + ConsoleColors.RESET);
                                break;
                            }

                            break;
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
                case 10:

                    System.out.println("All Requests: \n");
                    for (Request request:requests) {
                        System.out.println(request.viewDetails());
                    }
                    break;
                case 0:
                    MainMenuUI menuUI = new MainMenuUI();
                    menuUI.enterMenu();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void displayRequestHistory(List<Request> fypCoordinatorController, String x, List<Request> fypCoordinatorController1, String x1) {
        if (fypCoordinatorController.size() > 0) {
            System.out.println(x);
            for (Request request : fypCoordinatorController1) {
                System.out.println(request.viewDetails());
            }
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + x1 + ConsoleColors.RESET);
            return;
        }
    }

    private void manageIncomingRequests(Supervisor supervisor, FYP_Coordinator fypCoordinator, List<Project> projects, List<Request> requests, List<Student> students) throws IOException {
        int pendingRequests = 0;

        if (fypCoordinatorController.getIncomingRequests(supervisor).size() > 0) {
            for (Request request : fypCoordinatorController.getIncomingRequests(supervisor)) {
                if (request.getStatus() == RequestStatus.PENDING) {
                    pendingRequests++;
                }
            }
        }

        if (pendingRequests > 0) {
            System.out.println("Pending requests: \n");
            for (Request request : fypCoordinatorController.getIncomingRequests(supervisor)) {
                if (request.getStatus() == RequestStatus.PENDING) {
                    System.out.println(request.viewDetails());
                }
            }
        }
        else {
            System.out.println(ConsoleColors.RED_BRIGHT + "You have no pending requests!" + ConsoleColors.RESET);
            return;
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
    }

    private void manageRequestCLI(User user, List<Request> requests, List<Project> projects, List<Student> students, String requestChoice) throws IOException {
        for (Request request : fypCoordinatorController.getIncomingRequests(fypCoordinator)) {
            if (Objects.equals(request.getSender().getUserID(), requestChoice) && request.getStatus() == RequestStatus.PENDING) {
                manageRequestCheck = 1;
                System.out.println(request.viewDetails());
                Integer manageChoice;

                switch (request.getType()) {
                    case REGISTER:
                        handleStudentRegistration(requests, projects, request);
                        break;
                    case DEREGISTER:
                        handleStudentDeRegistration(requests, projects, students, request);
                        break;
                    case TRANSFER_STUDENT:
                        handleStudentTransfer(requests, projects, request);
                        break;

                    case CHANGE_TITLE:
                        handleChangeTitle(requests, projects, request);
                    default:
                        fypCoordinatorController.rejectRequest(request, requests);
                }
                return;
            }
        }
        System.out.println(ConsoleColors.RED_BRIGHT + "There is no such request with the given UserID! Try again!\n" + ConsoleColors.RESET);
    }

    private void handleChangeTitle(List<Request> requests, List<Project> projects, Request request) throws IOException {
        Integer manageChoice;
        System.out.println("Do you want to \n1. Approve OR\n2. Reject");
        manageChoice = scanner.nextInt();
        scanner.nextLine();
        if (manageChoice == 1) {
            fypCoordinatorController.approveRequest(request, requests);
            fypCoordinatorController.updateTitle(request.getProject(), request.getBody(), projects);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Project title has been updated!"+ ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "Request to change project title has been rejected"+ ConsoleColors.RESET);
            fypCoordinatorController.rejectRequest(request, requests);
        }
    }

    private void handleStudentTransfer(List<Request> requests, List<Project> projects, Request request) throws IOException {
        Integer manageChoice;
        if (fypCoordinatorController.checkSupervisorAvailability(request.getProject().getReplacementSupervisor(), projects)) {
            System.out.println("Do you want to \n1. Approve OR\n2. Reject");
            manageChoice = scanner.nextInt();
            scanner.nextLine();
            if (manageChoice == 1) {
                fypCoordinatorController.approveRequest(request, requests);
                System.out.println("Transferring...\n");
                System.out.println(ConsoleColors.GREEN_BRIGHT + "Transfer successful!\n"+ ConsoleColors.RESET);
                fypCoordinatorController.transferStudentToSupervisor(request.getProject(), projects);
            } else {
                System.out.println(ConsoleColors.RED_BRIGHT + "Request to transfer has been rejected.\n"+ ConsoleColors.RESET);
                fypCoordinatorController.rejectRequest(request, requests);
            }
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "Replacement Supervisor cap is reached!"+ ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BRIGHT + "Do you still want to allocate the project? \n1. Approve, 2. \nReject"+ ConsoleColors.RESET);

            manageChoice = scanner.nextInt();
            scanner.nextLine();

            if (manageChoice == 1) {
                fypCoordinatorController.approveRequest(request, requests);
                System.out.println("Registering...");
                fypCoordinatorController.registerStudentProject((Student) request.getSender(), request.getProject(), projects);
                System.out.println(ConsoleColors.GREEN_BRIGHT + "Successfully Registered!" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED_BRIGHT + "Request has been rejected. \n"+ ConsoleColors.RESET);
                fypCoordinatorController.rejectRequest(request, requests);
                fypCoordinatorController.unreserveProject(request.getProject(), projects);
            }
        }
    }

    private void handleStudentDeRegistration(List<Request> requests, List<Project> projects, List<Student> students, Request request) throws IOException {
        Integer manageChoice;
        System.out.println("Do you want to \n1. Approve OR\n2. Reject");
        manageChoice = scanner.nextInt();
        scanner.nextLine();
        if (manageChoice == 1) {
            fypCoordinatorController.approveRequest(request, requests);
            System.out.println("Deregistering...");
            fypCoordinatorController.deregisterStudentProject((Student) request.getSender(), request.getProject(), projects, students);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Successfully Deregistered!\n"+ ConsoleColors.RESET);
        } else {
            fypCoordinatorController.rejectRequest(request, requests);
        }
    }

    private void handleStudentRegistration(List<Request> requests, List<Project> projects, Request request) throws IOException {
        Integer manageChoice;
        System.out.println("Do you want to \n1. Approve OR\n2. Reject");
        manageChoice = scanner.nextInt();
        scanner.nextLine();
        if (manageChoice == 1) {

            if (fypCoordinatorController.checkSupervisorAvailability(request.getProject().getSupervisor(), projects)) {
                fypCoordinatorController.approveRequest(request, requests);
                System.out.println("Registering...");
                fypCoordinatorController.registerStudentProject((Student) request.getSender(), request.getProject(), projects);
                System.out.println(ConsoleColors.GREEN_BRIGHT + "Successfully Registered!" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED_BRIGHT + "Supervisor cap is reached!"+ ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED_BRIGHT + "Do you still want to allocate the project? 1. Yes, 2. No"+ ConsoleColors.RESET);

                int allocateDecision = scanner.nextInt();
                scanner.nextLine();

                if (allocateDecision == 1) {
                    fypCoordinatorController.approveRequest(request, requests);
                    System.out.println("Registering...");
                    fypCoordinatorController.registerStudentProject((Student) request.getSender(), request.getProject(), projects);
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Successfully Registered!" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED_BRIGHT + "Request has been rejected. Project will now be available.\n"+ ConsoleColors.RESET);
                    fypCoordinatorController.rejectRequest(request, requests);
                    fypCoordinatorController.unreserveProject(request.getProject(), projects);
                }
            }
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "Request has been rejected. Project will now be available.\n"+ ConsoleColors.RESET);
            fypCoordinatorController.rejectRequest(request, requests);
            fypCoordinatorController.unreserveProject(request.getProject(), projects);
        }
    }
}