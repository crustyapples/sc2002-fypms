package src.Controller;

import src.Entity.Project;
import src.Entity.Request;
import src.Entity.RequestType;
import src.Entity.User;

import java.io.IOException;
import java.util.List;

/**
 * The interface Request controller.
 */
public interface IRequestController {
    /**
     * Create request request.
     *
     * @param sender      the sender
     * @param recipient   the recipient
     * @param requestType the request type
     * @param project     the project
     * @param body        the body
     * @param requests    the requests
     * @return the request
     * @throws IOException the io exception
     */
    Request createRequest(User sender, User recipient, RequestType requestType, Project project, String body, List<Request> requests) throws IOException;

    /**
     * Approve request.
     *
     * @param request  the request
     * @param requests the requests
     * @throws IOException the io exception
     */
    void approveRequest(Request request, List<Request> requests) throws IOException;

    /**
     * Reject request.
     *
     * @param request  the request
     * @param requests the requests
     * @throws IOException the io exception
     */
    void rejectRequest(Request request, List<Request> requests) throws IOException;
}
