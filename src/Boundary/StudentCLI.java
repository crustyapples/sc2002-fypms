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
import src.CustomExceptions.ProjectsException;
import src.CustomExceptions.ProjectsException.ProjectSelectionNotAllowedException;
import src.Entity.*;


public class StudentCLI {
    private Scanner scanner;
    private IStudentController studentController;
    private LoginCLI loginCLI;
    private Student student;

    public StudentCLI(IStudentController studentController, Student student, LoginCLI loginCLI) {
        this.studentController = studentController;
        this.student = student;
        this.loginCLI = loginCLI;
        scanner = new Scanner(System.in);
    }

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
                    System.out.println("Change Password: ");
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    String userID = student.getUserID();
                    studentController.changeUserPassword(userID, newPassword);
                    System.out.println("You will now be logged out. Please login again!");
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
                        while (projectChoice == -1) {
                            try {
                                System.out.println("Enter the ProjectID:");
                                projectChoice = scanner.nextInt();
                                List<Project> availableProjects = studentController.getAvailableProjects(projects);
                                found: {
                                    for (Project availableProject : availableProjects) {
                                        if (availableProject.getProjectID() == projectChoice) {
                                            System.out.println("Sending Request...");
                                            studentController.selectProjectForStudent(student, availableProject, coordinator, requests, projects);
                                            break found;
                                        }
                                    }

                                    System.out.println(ConsoleColors.RED_BRIGHT + "Invalid ProjectID! Please try again!\n" + ConsoleColors.RESET);
                                    projectChoice = -1;
                                }

                            } catch (InputMismatchException e) {
                                System.out.println(ConsoleColors.RED_BRIGHT + "Invalid input! Please enter a valid integer!\n"+ ConsoleColors.RESET);
                                scanner.next(); //Clear the invalid input
                                projectChoice = -1;
                            }
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
