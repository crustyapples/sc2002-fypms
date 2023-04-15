package src.Controller;
import src.Entity.*;
import java.io.IOException;
import java.util.List;

public interface IRequestDataHandler {
    List<Request> loadRequestsFromDatabase(List<User> users, List<Project> projects) throws IOException;

    void saveRequestsToDatabase(List<Request> requests) throws IOException;

    void saveRequestToDatabase(Request request) throws IOException;

    // Other methods
}
