package src.Boundary;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import src.ConsoleColors;
import src.Controller.SupervisorController;
import src.CustomExceptions.InvalidInputException;
import src.Entity.*;

/**
 * The SupervisorCLI class represents the command-line interface for a supervisor.
 */
public class SupervisorCLI {
    /**
     * The Scanner object used for input.
     */
    public Scanner scanner;
    /**
     * The SupervisorController object used for controlling supervisor functionality.
     */
    private SupervisorController supervisorController;
    /**
     * The Supervisor object representing the logged in supervisor.
     */
    private Supervisor supervisor;
    /**
     * The LoginCLI object representing the login interface for a supervisor.
     */
    private LoginCLI loginCLI;
    /**
     * The PasswordChangerCLI object representing the password change interface for a supervisor.
     */
    private PasswordChangerCLI passwordChangerCLI;
    /**
     * The ProjectUpdaterCLI object representing the project update interface for a supervisor.
     */
    private ProjectUpdaterCLI projectUpdaterCLI;
    /**
     * An integer for checking of a manage request.
     */
    private int manageRequestCheck = -1;

    /**
     * Constructs a new SupervisorCLI object
     *
     * @param supervisorController the supervisor controller
     * @param supervisor the supervisor
     * @param loginCLI the login interface
     * @param passwordChangerCLI the password change interface
     * @param projectUpdaterCLI the project update interface
     */
    public SupervisorCLI(SupervisorController supervisorController, Supervisor supervisor, LoginCLI loginCLI, PasswordChangerCLI passwordChangerCLI, ProjectUpdaterCLI projectUpdaterCLI) {
        this.supervisorController = supervisorController;
        this.supervisor = supervisor;
        this.loginCLI = loginCLI;
        this.passwordChangerCLI = passwordChangerCLI;
        this.projectUpdaterCLI = projectUpdaterCLI;
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the supervisor menu
     */
    public void displayMenu() {
        System.out.println("1. Change password");
        System.out.println("2. Create a new project");
        System.out.println("3. Update an existing project");
        System.out.println("4. View your projects");
        int pendingRequests = 0;
        if (supervisorController.getIncomingRequests(supervisor).size() > 0) {
            for (Request request : supervisorController.getIncomingRequests(supervisor)) {
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
        System.out.println("0. Exit");
    }

    /**
     * This method is responsible for handling supervisor actions.

     * @param coordinator The coordinator object.
     * @param supervisors The list of supervisor objects.
     * @param projects The list of project objects.
     * @param requests The list of request objects.
     * @throws IOException If an input or output exception occurs.
     * */

    public void handleSupervisorActions(FYP_Coordinator coordinator, List<Supervisor> supervisors, List<Project> projects, List<Request> requests) throws IOException {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        //System.out.println("Change Password: ");
                        passwordChangerCLI.changePassword();
                        loginCLI.authenticateUser();
                        break;
                    case 2:
                        // Call supervisorController.createNewProject() with the project title
                        createProject(projects);
                        break;
                    case 3:
                        // Call supervisorController.updateExistingProject() with the new title
                        projectUpdaterCLI.updateProjects(projects);
                        break;
                    case 4:
                        // Call supervisorController.viewSupervisorProjects() and display the result
                        System.out.println("Your projects: \n");
                        supervisorController.viewSupervisorProjects(supervisor);
                        break;
                    case 5:
                        manageIncomingRequests(projects, requests);
                        break;
                    case 6:

                        displayIncomingRequests();
                        break;

                    case 7:
                        displayOutgoingRequests();
                        break;
                    case 8:
                        requestStudentTransfer(coordinator, supervisors, projects, requests);

                        break;
                    case 0:
                        MainMenuUI menuUI = new MainMenuUI();
                        menuUI.enterMenu();
                        exit = true;
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_BRIGHT +"Invalid choice! Please try again!\n" + ConsoleColors.RESET);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please enter a valid integer!\n" + ConsoleColors.RESET);
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * Allows the supervisor to manage incoming student requests by approving or rejecting them.
     * @param user the supervisor making the request
     * @param requests the list of all requests
     * @param projects the list of all projects
     * @param requestChoice the student ID of the request to be managed
     * @param supervisorController the SupervisorController object used to perform supervisor-related operations
     * @throws IOException if an I/O error occurs while reading input
     */

    private void manageRequestCLI(User user, List<Request> requests, List<Project> projects, String requestChoice, SupervisorController supervisorController) throws IOException {
        for (Request request : supervisorController.getIncomingRequests(user)) {
            if (Objects.equals(request.getSender().getUserID(), requestChoice) && request.getStatus() == RequestStatus.PENDING) {
                manageRequestCheck = 1;
                System.out.println(request.viewDetails());
                System.out.println("Do you want to \n1. Approve OR\n2. Reject");
                Integer manageChoice = scanner.nextInt();
                if (manageChoice == 1) {
                    supervisorController.approveRequest(request,requests);
                    supervisorController.updateTitle(request.getProject(),request.getBody(),projects);
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Request approved!\n" + ConsoleColors.RESET);
                    return;
                } else if (manageChoice == 2) {
                    supervisorController.rejectRequest(request,requests);
                    System.out.println(ConsoleColors.RED_BRIGHT + "Request rejected!\n" + ConsoleColors.RESET);
                    return;
                }
                return;
            }

        }
        System.out.println(ConsoleColors.RED_BRIGHT + "There is no request with the given StudentID! Try again!\n" + ConsoleColors.RESET);
    }

    /**
     * Displays all incoming requests for the supervisor.
     * If there are no incoming requests, prints a message to inform the user.
     */

    private void displayIncomingRequests() {
        if (supervisorController.getIncomingRequests(supervisor).size() > 0) {
            System.out.println("Incoming requests: \n");
            for (Request request : supervisorController.getIncomingRequests(supervisor)) {
                System.out.println(request.viewDetails());

            }
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "You have no incoming requests!" + ConsoleColors.RESET);
        }
    }

    /**
     * Requests for a student transfer from another supervisor's project to one of the supervisor's own projects.
     *
     * @param coordinator The FYP coordinator overseeing the project.
     * @param supervisors The list of all supervisors in the system.
     * @param projects The list of all projects in the system.
     * @param requests The list of all project requests in the system.
     * @throws IOException If an I/O error occurs.
     */

    private void requestStudentTransfer(FYP_Coordinator coordinator, List<Supervisor> supervisors, List<Project> projects, List<Request> requests) throws IOException {
        Integer projectChoice;
        supervisorController.viewSupervisorProjects(supervisor);

        if (supervisor.getProjects().size() == 0) {
            System.out.println("You have no projects!");
            return;
        }

        projectChoice = -1;
        transferStudentCLI(supervisor, coordinator, supervisors, projects, requests, projectChoice, scanner, supervisorController);
    }

    /**
     * Displays a list of the supervisor's outgoing project requests.
     */

    private void displayOutgoingRequests() {
        if (supervisorController.getRequestHistory(supervisor).size() > 0) {
            System.out.println("Outgoing requests: \n");
            for (Request request : supervisorController.getRequestHistory(supervisor)) {
                System.out.println(request.viewDetails());
            }
        } else {
            System.out.println(ConsoleColors.RED_BRIGHT + "You have no outgoing requests!" + ConsoleColors.RESET);
            return;
        }
    }

    /**
     * Allows the supervisor to manage incoming project requests. Displays pending requests and prompts the user to manage them or not.
     *
     * @param projects the list of projects to check for requests
     *
     * @param requests the list of requests to manage
     *
     * @throws IOException if there is an error reading input from the console
     */

    private void manageIncomingRequests(List<Project> projects, List<Request> requests) throws IOException {
        int pendingRequests = 0;

        if (supervisorController.getIncomingRequests(supervisor).size() > 0) {
            for (Request request : supervisorController.getIncomingRequests(supervisor)) {
                if (request.getStatus() == RequestStatus.PENDING) {
                    pendingRequests++;
                }
            }
        }

        if (pendingRequests > 0) {
            System.out.println("Pending requests: \n");
            for (Request request : supervisorController.getIncomingRequests(supervisor)) {
                if (request.getStatus() == RequestStatus.PENDING) {
                    System.out.println(request.viewDetails());
                }
            }
        }
        else {
            System.out.println(ConsoleColors.RED_BRIGHT + "You have no pending requests!" + ConsoleColors.RESET);
            return;
        }


        Integer manageChoice = -1;
        while (manageChoice == -1) {
            try {
                System.out.println("Do you want to manage the requests? 1. Yes 2. No");
                manageChoice = scanner.nextInt();
                scanner.nextLine();

                if (manageChoice == 1) {

                    Integer studentFound = -1;
                    while (studentFound == -1) {
                        try {
                            while (manageRequestCheck == -1) {
                                System.out.println("Enter the StudentID of the request that you want to handle: ");
                                String requestChoice = scanner.nextLine();
                                manageRequestCLI(supervisor, requests, projects, requestChoice, supervisorController);
                                studentFound = 1;
                            }

                        } catch (InputMismatchException e) {
                            studentFound = -1;
                            System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please input an integer!\n" + ConsoleColors.RESET);
                            scanner.next(); //Clear the invalid input
                        }
                    }
                } else if (manageChoice == 2) {
                    break;
                } else {
                    manageChoice = -1;
                    System.out.println(ConsoleColors.RED_BRIGHT + "Invalid choice! Please key in either 1 or 2.\n" + ConsoleColors.RESET);
                }

            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please input an integer!\n"+ ConsoleColors.RESET);
                scanner.next(); //Clear the invalid input

            }

        }
    }

    /**
     * Allows the user to update a project's title by choosing the project using its ID and providing a new title.
     *
     * @param projects the list of projects to search for the project to update and add the new project to
     * @throws IOException if there is an error reading input from the console
     */

    private void updateProjects(List<Project> projects) throws IOException {
        supervisorController.viewSupervisorProjects(supervisor);

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
                                supervisorController.updateTitle(project, newTitle, projects);
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
    }
    /**
     *      Creates a new project with the given title and adds it to the list of projects.
     *      @param projects the list of projects to add the new project to
     *      @throws IOException if there is an error reading input from the console
     */

    private void createProject(List<Project> projects) throws IOException {
        System.out.println("Enter the Project Title: ");
        String title = scanner.nextLine();
        supervisorController.createProject(supervisor, title, projects);
    }

    /**

     This method allows a supervisor to transfer a student to another supervisor.

     @param supervisor the supervisor who wants to transfer the student
     @param coordinator the FYP coordinator
     @param supervisors a list of all the supervisors in the system
     @param projects a list of all the available projects
     @param requests a list of all the transfer requests
     @param projectChoice the project ID of the project to be transferred
     @param scanner a Scanner object to read input from the user
     @param supervisorController the controller object to handle supervisor actions
    */
    static void transferStudentCLI(Supervisor supervisor, FYP_Coordinator coordinator, List<Supervisor> supervisors, List<Project> projects, List<Request> requests, Integer projectChoice, Scanner scanner, SupervisorController supervisorController) throws IOException {
        while (projectChoice == -1) {
            try {
                System.out.println("Enter the project ID of the project you wish to transfer: ");
                projectChoice = scanner.nextInt();
                scanner.nextLine();

                projectFound: {

                    try {
                        for (Project project : supervisor.getProjects()) {
                            if (project.getProjectID() == projectChoice) {
                                System.out.println(project.viewDetails());
                                int supervisorFound = -1;
                                while (supervisorFound == -1) {
                                    System.out.println("Enter the supervisorID of your replacement: ");
                                    String replacementSupervisorID = scanner.nextLine();
                                    found: {

                                        try {
                                            for (Supervisor replacementSupervisor : supervisors) {
                                                if (Objects.equals(replacementSupervisor.getUserID(), replacementSupervisorID) && !Objects.equals(project.getSupervisor().getUserID(), replacementSupervisorID)) {
                                                    System.out.println("Requesting...");
                                                    supervisorController.requestStudentTransferToAnotherSupervisor(supervisor, project, replacementSupervisor, coordinator, requests, projects);
                                                    supervisorFound = 1;
                                                    break found;
                                                }
                                            }

                                            throw new InvalidInputException.InvalidSupervisorIDException();

                                        } catch (InvalidInputException.InvalidSupervisorIDException e) {
                                            supervisorFound = -1;
                                            System.out.println(e.getMessage());
                                        }

                                    }

                                }
                                break projectFound;
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
    }


}
