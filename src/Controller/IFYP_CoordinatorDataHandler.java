package src.Controller;

import src.Entity.FYP_Coordinator;

import java.io.IOException;
import java.util.List;

public interface IFYP_CoordinatorDataHandler {
    List<FYP_Coordinator> loadCoordinatorsFromDatabase() throws IOException;
    void saveCoordinatorsToDatabase(List<FYP_Coordinator> coordinators) throws IOException;
}