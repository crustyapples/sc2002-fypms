package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.List;

/**
 * The type Request controller.
 */
public class RequestController implements IRequestController{
    private IRequestDataHandler requestDataHandler;

    /**
     * Instantiates a new Request controller.
     *
     * @param requestDataHandler the request data handler
     * @throws IOException the io exception
     */
    public RequestController(IRequestDataHandler requestDataHandler) throws IOException {
        this.requestDataHandler = requestDataHandler;
    }

    /**
     * Create request.
     *
     * @param sender the sender
     * @param recipient the recipient
     * @param requestType request type
     * @param project the project
     * @param body body text
     * @param requests list of requests
     * @return request the request
     * @throws IOException the io exception
     */
    public Request createRequest(User sender, User recipient, RequestType requestType, Project project, String body, List<Request> requests) throws IOException {
        Integer requestID = requests.size() + 1;
        Request request = new Request(requestID, sender, recipient, requestType, project, RequestStatus.PENDING, body);
        requests.add(request);
        requestDataHandler.saveRequestToDatabase(request);

        return request;
    }
    /**
     * Approve request.
     *
     * @param request the request
     * @param requests the list of requests
     * @throws IOException the io exception
     */
    public void approveRequest(Request request, List<Request> requests) throws IOException {
        request.approve();
        requestDataHandler.saveRequestsToDatabase(requests);
    }

    /**
     * Reject request.
     *
     * @param request the request
     * @param requests the list of requests
     * @throws IOException the io exception
     */
    public void rejectRequest(Request request, List<Request> requests) throws IOException {
        request.reject();
        requestDataHandler.saveRequestsToDatabase(requests);
    }
}