package src.Controller;

import src.Entity.FYP_Coordinator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Fyp coordinator data handler.
 */


public class FYP_CoordinatorDataHandler implements IFYP_CoordinatorDataHandler{
    private static final String COORDINATOR_FILE = "database/FYP_Coordinators_List.txt";

    /**
     * Load coordinators from database list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<FYP_Coordinator> loadCoordinatorsFromDatabase() throws IOException {
        List<FYP_Coordinator> coordinators = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COORDINATOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                String name = data[0];
                String email = data[1];
                String userID = email.substring(0, email.indexOf('@'));
                String password = "password";

                FYP_Coordinator coordinator = new FYP_Coordinator(userID,name, email,password);
                coordinators.add(coordinator);
            }
        }
        return coordinators;
    }
    /**
     * Save coordinators to database list.
     * @param coordinators the coordinator
     * @throws IOException the io exception
     */
    public void saveCoordinatorsToDatabase(List<FYP_Coordinator> coordinators) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COORDINATOR_FILE))) {
            for (FYP_Coordinator coordinator : coordinators) {
                writer.write(coordinator.getName() + "\t" + coordinator.getEmail());
                writer.newLine();
            }
        }
    }
}

