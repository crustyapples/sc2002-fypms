package src.Controller;

import src.Entity.Supervisor;

import java.io.IOException;
import java.util.List;

public interface ISupervisorDataHandler {
    List<Supervisor> loadFacultyFromDatabase() throws IOException;
    void saveFacultyToDatabase(List<Supervisor> faculty) throws IOException;
}
