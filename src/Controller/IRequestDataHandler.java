package src.Controller;
import src.Entity.*;
import java.io.IOException;
import java.util.List;

/**
 * The interface Request data handler.
 */
public interface IRequestDataHandler {
    /**
     * Load requests from database list.
     *
     * @param users    the users
     * @param projects the projects
     * @return the list
     * @throws IOException the io exception
     */
    List<Request> loadRequestsFromDatabase(List<User> users, List<Project> projects) throws IOException;

    /**
     * Save requests to database.
     *
     * @param requests the requests
     * @throws IOException the io exception
     */
    void saveRequestsToDatabase(List<Request> requests) throws IOException;

    /**
     * Save request to database.
     *
     * @param request the request
     * @throws IOException the io exception
     */
    void saveRequestToDatabase(Request request) throws IOException;

    // Other methods
}
