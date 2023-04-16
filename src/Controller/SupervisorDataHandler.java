package src.Controller;

import src.Entity.Supervisor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Supervisor data handler.
 */
public class SupervisorDataHandler implements ISupervisorDataHandler {
    private static final String FACULTY_FILE = "database/Faculty_List.txt";

    /**
     * Load supervisors from database.
     * @return faculty the supervisors
     * @throws IOException the io exception
     */
    public List<Supervisor> loadFacultyFromDatabase() throws IOException {
        List<Supervisor> faculty = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FACULTY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\t");
                String name = data[0];
                String email = data[1];
                String userID = email.substring(0, email.indexOf('@'));
                String password = "password";

                Supervisor supervisor = new Supervisor(userID,name, email,password);
                faculty.add(supervisor);
            }
        }
        return faculty;
    }

    /**
     * Save faculty to database.
     * @param faculty the supervisors
     * @throws IOException the io exception
     */
    public void saveFacultyToDatabase(List<Supervisor> faculty) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FACULTY_FILE))) {
            for (Supervisor supervisor : faculty) {
                writer.write(supervisor.getName() + "\t" + supervisor.getEmail());
                writer.newLine();
            }
        }
    }
}
