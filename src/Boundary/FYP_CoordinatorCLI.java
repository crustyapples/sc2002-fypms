package src.Boundary;

import java.util.Scanner;

import src.Controller.FYP_CoordinatorController;
import src.Entity.FYP_Coordinator;
import src.Entity.Supervisor;

public class FYP_CoordinatorCLI extends SupervisorCLI {
    private FYP_CoordinatorController fypCoordinatorController;

    public FYP_CoordinatorCLI(FYP_CoordinatorController fypCoordinatorController) {
        super(fypCoordinatorController);
        this.fypCoordinatorController = fypCoordinatorController;
    }

    @Override
    public void displaySupervisorMenu() {
        super.displaySupervisorMenu();
        System.out.println("7. Allocate project to student");
        System.out.println("8. Deallocate project from student");
        System.out.println("9. View projects by filter");
        System.out.println("10. Generate project report");
    }

    @Override
    public void handleSupervisorActions(Supervisor supervisor) {
        FYP_Coordinator coordinator = (FYP_Coordinator) supervisor;
        boolean exit = false;
        while (!exit) {
            displaySupervisorMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 7:
                    // Call fypCoordinatorController.allocateProjectToStudent() with the student and project
                    break;
                case 8:
                    // Call fypCoordinatorController.deallocateProjectFromStudent() with the student and project
                    break;
                case 9:
                    // Call fypCoordinatorController.viewProjectsByFilter() with the filter and display the result
                    break;
                case 10:
                    // Call fypCoordinatorController.generateProjectReport() and display the result
                    break;
                default:
                    super.handleSupervisorActions(coordinator);
                    break;
            }
        }
    }
}
