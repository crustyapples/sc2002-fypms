package src;

import src.Boundary.FYP_CoordinatorCLI;
import src.Boundary.StudentCLI;
import src.Boundary.SupervisorCLI;
import src.Controller.*;
import src.Boundary.LoginCLI;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
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

        IProjectController projectController = new ProjectController();
        IRequestController requestController = new RequestController();
        IStudentController studentController = new StudentController();

        if (user instanceof Student) {
            StudentCLI studentMenu = new StudentCLI(studentController, (Student) user);
            studentMenu.handleStudentActions((Student) user, projects, requests, coordinators.get(0));
        }

        else if (user instanceof FYP_Coordinator) {
            FYP_CoordinatorController fypCoordinatorController = new FYP_CoordinatorController();
            FYP_CoordinatorCLI fypCoordinatorMenu = new FYP_CoordinatorCLI(fypCoordinatorController, (FYP_Coordinator) user);
            fypCoordinatorMenu.handleSupervisorActions((FYP_Coordinator) user,(FYP_Coordinator) user, supervisors, projects, requests, students);
        }

        else if (user instanceof Supervisor) {
            SupervisorController supervisorController = new SupervisorController(studentController, projectController, requestController);
            SupervisorCLI supervisorMenu = new SupervisorCLI(supervisorController, (Supervisor) user);
            supervisorMenu.handleSupervisorActions((Supervisor) user, coordinators.get(0), supervisors, projects, requests);
        }

    }
}
