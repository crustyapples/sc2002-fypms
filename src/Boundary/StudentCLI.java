package src.Boundary;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.Controller.IStudentController;
import src.Controller.StudentController;
import src.Controller.UserController;
import src.Controller.UserDataHandler;
import src.Entity.*;


public class StudentCLI {
    private Scanner scanner;
    private IStudentController studentController;
    private Student student;

    public StudentCLI(IStudentController studentController, Student student) {
        this.studentController = studentController;
        this.student = student;
        scanner = new Scanner(System.in);
    }

    public void displayStudentMenu() {
        System.out.println("Welcome, " + student.getName() + "!");
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
                    exit = true;
                    break;

                case 2:

                    if (student.getRegistered()) {
                        System.out.println("You are currently allocated to a FYP and do not have access to available project list");

                    } else if (student.getDeRegistered()) {
                        System.out.println("You are not allowed to make a selection again as you deregistered your FYP.");

                    } else {
                        List<Project> availableProjects = studentController.getAvailableProjects(projects);
                        System.out.println("Available Projects: ");
                        for (Project availableProject : availableProjects) {
                            System.out.println(availableProject.viewDetails());
                        }
                    }

                    break;
                case 3:
                    // Call studentController.selectProjectForStudent() with the selected project
                    System.out.println("Enter the ProjectID:");
                    int projectChoice = scanner.nextInt();
                    List<Project> availableProjects = studentController.getAvailableProjects(projects);

                    for (Project availableProject : availableProjects) {
                        if (availableProject.getProjectID() == projectChoice) {
                            System.out.println("Sending Request...");
                            studentController.selectProjectForStudent(student, availableProject, coordinator,requests, projects);
                        }
                    }

                    break;
                case 4:
                    // Call studentController.isHashProject() and display the result
                    if (!student.getRegistered()) {
                        System.out.println("You have not registered a project!");
                    } else {
                        System.out.println(student.getSelectedProject().viewDetails());
                    }
                    break;
                case 5:
                    // Call studentController.requestProjectTitleChange() with the new title
                    System.out.println("Enter new project title:");
                    String newTitle = scanner.nextLine();
                    studentController.requestProjectTitleChange(student, newTitle, requests);

                    break;
                case 6:
                    // Call studentController.requestProjectDeregistration()
                    studentController.requestProjectDeregistration(student,coordinator,requests);
                    break;
                case 7:
                    // Call studentController.viewStudentRequestHistory() and display the result
                    studentController.viewStudentRequestHistory(student);
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
