//package src.Test;
//import src.Boundary.*;
//import src.Controller.*;
//import src.Entity.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class RequestsTest {
//    public static void main(String[] args) throws IOException {
//
//        SupervisorDataHandler supervisorDataHandler = new SupervisorDataHandler();
//        List<Supervisor> supervisors = supervisorDataHandler.loadFacultyFromDatabase();
//
//        FYP_CoordinatorDataHandler fypCoordinatorDataHandler = new FYP_CoordinatorDataHandler();
//        List<FYP_Coordinator> fypCoordinators = fypCoordinatorDataHandler.loadCoordinatorsFromDatabase();
//
//        StudentDataHandler studentDataHandler = new StudentDataHandler();
//        List<Student> students = studentDataHandler.loadStudentsFromDatabase();
//
//
//
//        List<Supervisor> supervisorsToRemove = new ArrayList<>();
//
//        for (Supervisor supervisor : supervisors) {
//            for (FYP_Coordinator coordinator : fypCoordinators) {
//                if (supervisor.getUserID().equals(coordinator.getUserID())) {
//                    supervisorsToRemove.add(supervisor);
//                }
//            }
//        }
//
//        supervisors.removeAll(supervisorsToRemove);
//
//
//
//
//        List<User> users = new ArrayList<>();
//
//        // Add all the supervisors to the users list
//        users.addAll(supervisors);
//
//        // Add all the FYP coordinators to the users list
//        users.addAll(fypCoordinators);
//
//        // Add all the students to the users list
//        users.addAll(students);
//
//        ProjectDataHandler projectDataHandler = new ProjectDataHandler();
//        List<Project> projects = projectDataHandler.loadProjectsFromDatabase(users);
//
//        RequestDataHandler requestDataHandler = new RequestDataHandler();
//        List<Request> requests = requestDataHandler.loadRequestsFromDatabase(users, projects);
//
//        Student testStudent = students.get(6);
//        StudentController sc = new StudentController();
//        StudentCLI studentMenu = new StudentCLI(sc, testStudent);
//
//        Supervisor testSupervisor = supervisors.get(0);
//        SupervisorController supervisorController = new SupervisorController();
//        SupervisorCLI supervisorCLI = new SupervisorCLI(supervisorController, testSupervisor);
//
//        FYP_Coordinator testFypCoordinator = fypCoordinators.get(0);
//        FYP_CoordinatorController fypCoordinatorController = new FYP_CoordinatorController();
//        FYP_CoordinatorCLI fypCoordinatorCLI = new FYP_CoordinatorCLI(fypCoordinatorController, testFypCoordinator);
//
//        Scanner scanner;
//        scanner = new Scanner(System.in);
//        boolean exit = false;
//        while (!exit) {
//
//            System.out.println("1. Student, 2. Supervisor, 3. Coordinator 0. Log Off");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    studentMenu.handleStudentActions(testStudent, projects, requests, testFypCoordinator);
//                    break;
//                case 2:
//                    supervisorCLI.handleSupervisorActions(testSupervisor, testFypCoordinator, supervisors, projects, requests);
//                    break;
//                case 3:
//                    fypCoordinatorCLI.handleSupervisorActions(testFypCoordinator, testFypCoordinator, supervisors, projects, requests, students);
//                    break;
//                case 0:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//
//        }
//    }
//}
