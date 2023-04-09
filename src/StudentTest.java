package src;

import src.Boundary.*;
import src.Controller.*;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentTest {
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
        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(supervisors);

        RequestDataHandler requestDataHandler = new RequestDataHandler();
        List<Request> requests = requestDataHandler.loadRequestsFromDatabase(users,projects);

        Student testStudent = new Student("YCHERN","password","CHERN","YCHERN@e.ntu.edu.sg");
        StudentController sc = new StudentController();
        StudentCLI studentMenu = new StudentCLI(sc);

        studentMenu.handleStudentActions(testStudent,projects, requests, fypCoordinators.get(0));
    }
}
