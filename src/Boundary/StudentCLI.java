package src.Boundary;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.ConsoleColors;
import src.Controller.IStudentController;
import src.Controller.StudentController;
import src.Controller.UserController;
import src.Controller.UserDataHandler;
import src.CustomExceptions.InvalidInputException;
import src.CustomExceptions.ProjectsException;
import src.CustomExceptions.ProjectsException.ProjectSelectionNotAllowedException;
import src.Entity.*;


/**
 * This class represents a command-line interface (CLI) for a student in the FYP management system.
 */
public class StudentCLI {
    /**
     * Scanner object used for user input.
     */
    private Scanner scanner;
    /**
     * Controller object for managing student data.
     */
    private IStudentController studentController;
    /**
     * Command-line interface for handling student login.
     */
    private LoginCLI loginCLI;
    /**
     * Command-line interface for changing student password.
     */
    private PasswordChangerCLI passwordChangerCLI;
    /**
     * Object representing the currently logged-in student.
     */
    private Student student;

    /**
     * Constructs a new StudentCLI object.
     *
     * @param studentController the student controller used to interact with student data
     * @param student the student associated with the CLI
     * @param loginCLI the login CLI used to handle student login
     * @param passwordChangerCLI the password changer CLI used to handle changing the student's password
     */
    public StudentCLI(IStudentController studentController, Student student, LoginCLI loginCLI, PasswordChangerCLI passwordChangerCLI) {
        this.studentController = studentController;
        this.student = student;
        this.loginCLI = loginCLI;
        this.passwordChangerCLI = passwordChangerCLI;
        scanner = new Scanner(System.in);
    }

    /**
     * This method displays a menu of options for the student to choose from
     */
    public void displayStudentMenu() {

        System.out.println("1. Change password");
        System.out.println("2. View available projects");
        System.out.println("3. Select a project to send to the coordinator for approval");
        System.out.println("4. View your own project");
        System.out.println("5. Request project title change");
        System.out.println("6. Request coordinator to deregister FYP");
        System.out.println("7. View request history");
        System.out.println("0. Exit");
    }

    /**
     *
     * Handles the actions of the student.
     *
     * @param student the student
     * @param projects the list of available projects
     * @param requests the list of requests made by the student
     * @param coordinator the faculty coordinator
     * @throws IOException if there is an error with input or output
     */

    public void handleStudentActions(Student student, List<Project> projects, List<Request> requests, FYP_Coordinator coordinator) throws IOException {
        boolean exit = false;
        while (!exit) {
            displayStudentMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    passwordChangerCLI.changePassword();
                    loginCLI.authenticateUser();
                    break;

                    case 2:

                        try {
                            if (student.getRegistered()) {
                                throw new ProjectsException.NoAccessToProjectListException();
                            } else if (student.getDeRegistered()) {
                                throw new ProjectsException.ProjectSelectionNotAllowedException();
                            } else {
                                List<Project> availableProjects = studentController.getAvailableProjects(projects);
                                System.out.println("Available Projects: \n");
                                for (Project availableProject : availableProjects) {
                                    System.out.println(availableProject.viewDetails());
                                }
                            }
                        }
                        catch (ProjectsException.NoAccessToProjectListException e) {
                            System.out.println(e.getMessage());
                        } catch (ProjectSelectionNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }


                        break;
                    case 3:
                        int projectChoice = -1;
                            try {
                                try {
                                    if (student.getRegistered()) {
                                        throw new ProjectsException.NoAccessToProjectListException();
                                    } else if (student.getDeRegistered()) {
                                        throw new ProjectsException.ProjectSelectionNotAllowedException();
                                    } else {
                                        while (projectChoice == -1) {
                                            System.out.println("\nEnter 0 to go back to view available projects");
                                            System.out.println("Enter the ProjectID:");
                                        projectChoice = scanner.nextInt();
                                        if (projectChoice == 0) {
                                            break;
                                        } else {
                                            List<Project> availableProjects = studentController.getAvailableProjects(projects);
                                            try {
                                                found:
                                                {
                                                    for (Project availableProject : availableProjects) {
                                                        if (availableProject.getProjectID() == projectChoice) {
                                                            System.out.println("Sending Request...");
                                                            studentController.selectProjectForStudent(student, availableProject, coordinator, requests, projects);
                                                            break found;
                                                        }
                                                    }

                                                    throw new InvalidInputException.InvalidProjectIDException();
                                                }

                                            } catch (InvalidInputException.InvalidProjectIDException e) {
                                                projectChoice = -1;
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        }
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please enter a valid integer!\n"+ ConsoleColors.RESET);
                                    scanner.next(); //Clear the invalid input
                                    projectChoice = -1;
                                }

                            } catch (ProjectsException.NoAccessToProjectListException e) {
                                System.out.println(e.getMessage());
                            } catch (ProjectSelectionNotAllowedException e) {
                                System.out.println(e.getMessage());
                            }





                        break;
                    case 4:
                        // Call studentController.isHashProject() and display the result
                        try {
                            if (!student.getRegistered()) {
                                throw new ProjectsException.ProjectNotRegisteredException();
                            } else {
                                System.out.println(student.getSelectedProject().viewDetails());
                            }
                        } catch (ProjectsException.ProjectNotRegisteredException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 5:
                        // Call studentController.requestProjectTitleChange() with the new title

                        try {
                            if (student.getSelectedProject() != null) {
                                System.out.println("Enter new project title:");
                                String newTitle = scanner.nextLine();
                                studentController.requestProjectTitleChange(student, newTitle, requests);
                            } else {
                                throw new ProjectsException.ProjectNotRegisteredException();
                            }
                        } catch (ProjectsException.ProjectNotRegisteredException e) {
                            System.out.println(e.getMessage());
                        }


                        break;
                    case 6:
                        // Call studentController.requestProjectDeregistration()

                        try {
                            if (!student.getRegistered()) {
                                throw new ProjectsException.ProjectNotRegisteredException();
                            } else {
                                studentController.requestProjectDeregistration(student, coordinator, requests);
                            }
                        } catch (ProjectsException.ProjectNotRegisteredException e) {
                            System.out.println(e.getMessage());
                        }


                        break;
                    case 7:
                        // Call studentController.viewStudentRequestHistory() and display the result

                        if (studentController.getRequestHistory(student).size() > 0) {
                            System.out.println("Outgoing requests: \n");
                            for (Request request : studentController.getRequestHistory(student)) {
                                System.out.println(request.viewDetails());
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BRIGHT + "You have no outgoing requests!" + ConsoleColors.RESET);
                            break;
                        }

                        break;
                    case 0:
                        MainMenuUI menuUI = new MainMenuUI();
                        menuUI.enterMenu();
                        exit = true;
                        break;
                    default:
                        System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid choice. Please try again.\n" + ConsoleColors.RESET);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input. Please enter a valid integer.\n" + ConsoleColors.RESET);
                scanner.nextLine(); //Consume the invalid input
            }
        }
    }
}
