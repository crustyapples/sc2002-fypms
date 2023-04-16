package src.Boundary;

import src.ConsoleColors;
import src.Controller.*;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MainMenuUI class represents the main menu interface of the FYP management system.
 * It allows users to login, change their passwords, and perform actions depending on their role.
 * It loads data from various data handlers and passes it to corresponding controllers.
 */
public class MainMenuUI {
    /**
     * Instantiates a new MainMenuUI.
     */
    public MainMenuUI(){

    }

    /**
     * Enter menu.
     *
     * @throws IOException the io exception
     */
    public void enterMenu() throws IOException {
        SupervisorDataHandler svData = new SupervisorDataHandler();
        FYP_CoordinatorDataHandler fypData = new FYP_CoordinatorDataHandler();
        StudentDataHandler stdData = new StudentDataHandler();

        List<Supervisor> supervisors = svData.loadFacultyFromDatabase();
        List<FYP_Coordinator> coordinators = fypData.loadCoordinatorsFromDatabase();
        List<Student> students = stdData.loadStudentsFromDatabase();

        List<Supervisor> supervisorsToRemove = new ArrayList<>();
        for (Supervisor supervisor : supervisors) {
            for (FYP_Coordinator coordinator : coordinators) {
                if (supervisor.getUserID().equals(coordinator.getUserID())) {
                    supervisorsToRemove.add(supervisor);
                }
            }
        }
        supervisors.removeAll(supervisorsToRemove);

        List<User> users = new ArrayList<>();
        users.addAll(supervisors);
        users.addAll(coordinators);
        users.addAll(students);

        ProjectDataHandler projectDataHandler = new ProjectDataHandler();
        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(users);

        RequestDataHandler requestDataHandler = new RequestDataHandler();
        List<Request> requests = requestDataHandler.loadRequestsFromDatabase(users, projects);

        UserController userController = new UserController();
        LoginCLI login = new LoginCLI(userController, users);
        User user = login.authenticateUser();
        PasswordChangerCLI passwordChangerCLI = new PasswordChangerCLI(user,userController);

        IProjectController projectController = new ProjectController(projectDataHandler);
        IRequestController requestController = new RequestController(requestDataHandler);
        IStudentController studentController = new StudentController(stdData,projectController, requestController);

        if (user instanceof Student) {
            StudentCLI studentMenu = new StudentCLI(studentController, (Student) user, login,passwordChangerCLI);
            studentMenu.handleStudentActions((Student) user, projects, requests, coordinators.get(0));
        }

        else if (user instanceof FYP_Coordinator) {
            FYP_CoordinatorController fypCoordinatorController = new FYP_CoordinatorController(studentController, projectController, requestController);
            ProjectUpdaterCLI projectUpdaterCLI = new ProjectUpdaterCLI((Supervisor) user, fypCoordinatorController);
            FYP_CoordinatorCLI fypCoordinatorMenu = new FYP_CoordinatorCLI(fypCoordinatorController, (FYP_Coordinator) user,login,passwordChangerCLI,projectUpdaterCLI);
            fypCoordinatorMenu.handleSupervisorActions((FYP_Coordinator) user,(FYP_Coordinator) user, supervisors, projects, requests, students);
        }

        else if (user instanceof Supervisor) {
            SupervisorController supervisorController = new SupervisorController(studentController, projectController, requestController);
            ProjectUpdaterCLI projectUpdaterCLI = new ProjectUpdaterCLI((Supervisor) user, supervisorController);
            SupervisorCLI supervisorMenu = new SupervisorCLI(supervisorController, (Supervisor) user, login, passwordChangerCLI,projectUpdaterCLI);
            supervisorMenu.handleSupervisorActions(coordinators.get(0), supervisors, projects, requests);
        }

    }

}
