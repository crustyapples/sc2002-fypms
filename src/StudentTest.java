package src;

import src.Boundary.FYP_CoordinatorDataHandler;
import src.Boundary.ProjectDataHandler;
import src.Boundary.StudentCLI;
import src.Boundary.SupervisorDataHandler;
import src.Controller.StudentController;
import src.Entity.FYP_Coordinator;
import src.Entity.Project;
import src.Entity.Student;
import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

public class StudentTest {
    public static void main (String[] args) throws IOException {
        SupervisorDataHandler supervisorDataHandler = new SupervisorDataHandler();
        List<Supervisor> supervisors = supervisorDataHandler.loadFacultyFromDatabase();

        ProjectDataHandler projectDataHandler = new ProjectDataHandler();
        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(supervisors);

        FYP_CoordinatorDataHandler fypCoordinatorDataHandler = new FYP_CoordinatorDataHandler();
        List<FYP_Coordinator> fypCoordinators = fypCoordinatorDataHandler.loadCoordinatorsFromDatabase();

        Student testStudent = new Student("YCHERN","password","CHERN","YCHERN@e.ntu.edu.sg");
        StudentController sc = new StudentController();
        StudentCLI studentMenu = new StudentCLI(sc);

        studentMenu.handleStudentActions(testStudent,projects, fypCoordinators.get(0));
    }
}
