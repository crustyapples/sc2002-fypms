package src.Test;

import src.Boundary.FYP_CoordinatorCLI;
import src.Boundary.SupervisorCLI;
import src.Controller.*;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class coordinatorTest {
    public static void main (String[] args) throws IOException {

        SupervisorDataHandler supervisorDataHandler = new SupervisorDataHandler();
        List<Supervisor> supervisors = supervisorDataHandler.loadFacultyFromDatabase();

        FYP_CoordinatorDataHandler fypCoordinatorDataHandler = new FYP_CoordinatorDataHandler();
        List<FYP_Coordinator> fypCoordinators = fypCoordinatorDataHandler.loadCoordinatorsFromDatabase();

        StudentDataHandler studentDataHandler = new StudentDataHandler();
        List<Student> students = studentDataHandler.loadStudentsFromDatabase();

        List<User> users = new ArrayList<>();

        // Add all the supervisors to the users list
        users.addAll(supervisors);

        // Add all the FYP coordinators to the users list
        users.addAll(fypCoordinators);

        // Add all the students to the users list
        users.addAll(students);


        ProjectDataHandler projectDataHandler = new ProjectDataHandler();
        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(users);

        RequestDataHandler requestDataHandler = new RequestDataHandler();
        List<Request> requests = requestDataHandler.loadRequestsFromDatabase(users,projects);

        FYP_Coordinator testCoordinator = fypCoordinators.get(0);
        FYP_CoordinatorController fypCoordinatorController = new FYP_CoordinatorController();
        FYP_CoordinatorCLI fypCoordinatorCLI = new FYP_CoordinatorCLI(fypCoordinatorController, testCoordinator);

        fypCoordinatorCLI.handleSupervisorActions(testCoordinator,fypCoordinators.get(0),supervisors,projects,requests, students);
    }
}
