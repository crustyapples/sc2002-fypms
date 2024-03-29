package src.Boundary;

import src.ConsoleColors;
import src.Controller.SupervisorController;
import src.CustomExceptions.InvalidInputException;
import src.Entity.Project;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The ProjectUpdaterCLI class provides a command-line interface for a supervisor to update projects.
 */
public class ProjectUpdaterCLI {
    private Supervisor supervisor;
    private SupervisorController supervisorController;
    private Scanner scanner;

    /**
     * Constructs a new ProjectUpdaterCLI object with the given supervisor and supervisor controller.
     *
     * @param supervisor the supervisor to be used
     * @param supervisorController the supervisor controller to be used
     */
    public ProjectUpdaterCLI(Supervisor supervisor, SupervisorController supervisorController) {
        this.supervisor = supervisor;
        this.supervisorController = supervisorController;
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user to select a project to update, then prompts them to enter a new project title.
     * The title of the selected project is updated to the new title.
     *
     * @param projects the list of projects to be used
     * @throws IOException if there is an error updating the project title
     */
    public void updateProjects(List<Project> projects) throws IOException {
        supervisorController.viewSupervisorProjects(supervisor);

        Integer projectChoice = -1;
        while (projectChoice == -1) {
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

    // Additional methods for handling other project update functionalities can be added here, if needed.
}
