package src.Controller;

import src.Entity.FYP_Coordinator;

import java.io.IOException;
import java.util.List;

/**
 * The interface Ifyp coordinator data handler.
 */
public interface IFYP_CoordinatorDataHandler {
    /**
     * Load coordinators from database list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    List<FYP_Coordinator> loadCoordinatorsFromDatabase() throws IOException;

    /**
     * Save coordinators to database.
     *
     * @param coordinators the coordinators
     * @throws IOException the io exception
     */
    void saveCoordinatorsToDatabase(List<FYP_Coordinator> coordinators) throws IOException;
}