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

public class SupervisorCLI {
    public Scanner scanner;
    private SupervisorController supervisorController;
    private Supervisor supervisor;
    private LoginCLI loginCLI;
    private int manageRequestCheck = -1;

    public SupervisorCLI(SupervisorController supervisorController, Supervisor supervisor, LoginCLI loginCLI) {
        this.supervisorController = supervisorController;
        this.supervisor = supervisor;
        this.loginCLI = loginCLI;
        scanner = new Scanner(System.in);
    }

    public void manageRequestCLI(User user, List<Request> requests, List<Project> projects, String requestChoice, SupervisorController supervisorController) throws IOException {
        for (Request request : supervisorController.getIncomingRequests(user)) {
            if (Objects.equals(request.getSender().getUserID(), requestChoice)) {
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

        System.out.println("6. View outgoing request history");
        System.out.println("7. Request student transfer");
        System.out.println("0. Exit");
    }

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
                        System.out.println("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        String userID = supervisor.getUserID();
                        supervisorController.changeUserPassword(userID, newPassword);
                        System.out.println("You will now be logged out. Please login again!");
                        loginCLI.authenticateUser();
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
                        break;
                    case 4:
                        // Call supervisorController.viewSupervisorProjects() and display the result
                        System.out.println("Your projects: \n");
                        supervisorController.viewSupervisorProjects(supervisor);
                        break;
                    case 5:

                        if (supervisorController.getIncomingRequests(supervisor).size() == 0) {
                            System.out.println(ConsoleColors.RED_BRIGHT + "You have no incoming requests!" + ConsoleColors.RESET);
                            break;
                        } else {
                            System.out.println("Incoming requests: \n");
                            for (Request request : supervisorController.getIncomingRequests(supervisor)) {
                                System.out.println(request.viewDetails());
                            }
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

                        break;


                    case 6:
                        // Call supervisorController.viewSupervisorRequestHistory() and display the result
                        if (supervisorController.getRequestHistory(supervisor).size() > 0) {
                            System.out.println("Outgoing requests: \n");
                            for (Request request : supervisorController.getRequestHistory(supervisor)) {
                                System.out.println(request.viewDetails());
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "You have no outgoing requests!" + ConsoleColors.RESET);
                            break;
                        }

                        break;
                    case 7:
                        // Call supervisorController.requestStudentTransferToAnotherSupervisor() with the new supervisor
                        supervisorController.viewSupervisorProjects(supervisor);

                        if (supervisor.getProjects().size() == 0) {
                            System.out.println("You have no projects!");
                            break;
                        }

                        projectChoice = -1;
                        transferStudentCLI(supervisor, coordinator, supervisors, projects, requests, projectChoice, scanner, supervisorController);

                        break;
                    case 0:
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
                                                if (Objects.equals(replacementSupervisor.getUserID(), replacementSupervisorID)) {
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
