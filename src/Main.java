package src;

import src.Controller.FYP_CoordinatorDataHandler;
import src.Boundary.LoginCLI;
import src.Controller.StudentDataHandler;
import src.Controller.SupervisorDataHandler;
import src.Controller.UserController;
import src.Entity.FYP_Coordinator;
import src.Entity.Student;
import src.Entity.Supervisor;
import src.Entity.User;

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

        UserController userController = new UserController();
        LoginCLI login = new LoginCLI(userController);

        login.authenticateUser(users,userController.userDataHandler.loadUserPasswordsFromDatabase(users));

    }
}
