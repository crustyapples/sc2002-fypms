package src.Controller;

import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

/**
 * The interface Supervisor data handler.
 */
public interface ISupervisorDataHandler {
    /**
     * Load faculty from database list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    List<Supervisor> loadFacultyFromDatabase() throws IOException;

    /**
     * Save faculty to database.
     *
     * @param faculty the faculty
     * @throws IOException the io exception
     */
    void saveFacultyToDatabase(List<Supervisor> faculty) throws IOException;
}
