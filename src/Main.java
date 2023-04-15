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

public class Main {
    public static void main(String[] args) throws IOException {
        SupervisorDataHandler svData = new SupervisorDataHandler();
        FYP_CoordinatorDataHandler fypData = new FYP_CoordinatorDataHandler();
        StudentDataHandler stdData = new StudentDataHandler();

        List<User> users = new ArrayList<>();

        List<Supervisor> supervisors = svData.loadFacultyFromDatabase();

        for (Supervisor supervisor : supervisors) {
            users.add((User) supervisor);
        }

        // Cast and add coordinators to users
        List<FYP_Coordinator> coordinators = fypData.loadCoordinatorsFromDatabase();
        for (FYP_Coordinator coordinator : coordinators) {
            users.add((User) coordinator);
        }

        // Cast and add students to users
        List<Student> students = stdData.loadStudentsFromDatabase();
        for (Student student : students) {
            users.add((User) student);
        }

        ProjectDataHandler projectDataHandler = new ProjectDataHandler();
        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(supervisors,students);

        RequestDataHandler requestDataHandler = new RequestDataHandler();
        List<Request> requests = requestDataHandler.loadRequestsFromDatabase(users, projects);

        UserController userController = new UserController();
        LoginCLI login = new LoginCLI(userController);

        User user = login.authenticateUser();

        if (user instanceof Student) {
            StudentController studentController = new StudentController();
            StudentCLI studentMenu = new StudentCLI(studentController, (Student) user);
            studentMenu.handleStudentActions((Student) user, projects, requests, coordinators.get(0));
        }

        else if (user instanceof FYP_Coordinator) {
            FYP_CoordinatorController fypCoordinatorController = new FYP_CoordinatorController();
            FYP_CoordinatorCLI fypCoordinatorMenu = new FYP_CoordinatorCLI(fypCoordinatorController, (FYP_Coordinator) user);
            fypCoordinatorMenu.handleSupervisorActions((FYP_Coordinator) user,(FYP_Coordinator) user, supervisors, projects, requests, students);
        }

        else if (user instanceof Supervisor) {
            SupervisorController supervisorController = new SupervisorController();
            SupervisorCLI supervisorMenu = new SupervisorCLI(supervisorController, (Supervisor) user);
            supervisorMenu.handleSupervisorActions((Supervisor) user, coordinators.get(0), supervisors, projects, requests);
        }

    }
}
