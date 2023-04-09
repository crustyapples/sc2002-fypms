package src;

import src.Boundary.*;
import src.Controller.StudentController;
import src.Controller.SupervisorController;
import src.Entity.FYP_Coordinator;
import src.Entity.Project;
import src.Entity.Student;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

public class SupervisorTest {
    public static void main (String[] args) throws IOException {
        SupervisorDataHandler supervisorDataHandler = new SupervisorDataHandler();
        List<Supervisor> supervisors = supervisorDataHandler.loadFacultyFromDatabase();

        ProjectDataHandler projectDataHandler = new ProjectDataHandler();
        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(supervisors);

        FYP_CoordinatorDataHandler fypCoordinatorDataHandler = new FYP_CoordinatorDataHandler();
        List<FYP_Coordinator> fypCoordinators = fypCoordinatorDataHandler.loadCoordinatorsFromDatabase();

        Supervisor testSupervisor = supervisors.get(0);
        SupervisorController supervisorController = new SupervisorController();
        SupervisorCLI supervisorCLI = new SupervisorCLI(supervisorController);

        supervisorCLI.handleSupervisorActions(testSupervisor,projects);
    }
}
