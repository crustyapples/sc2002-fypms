package src.Controller;

import src.Entity.*;

import java.io.IOException;
import java.util.List;

public class RequestController implements IRequestController{
    private IRequestDataHandler requestDataHandler;

    public RequestController(IRequestDataHandler requestDataHandler) throws IOException {
        this.requestDataHandler = requestDataHandler;
    }

    public Request createRequest(User sender, User recipient, RequestType requestType, Project project, String body, List<Request> requests) throws IOException {
        Integer requestID = requests.size() + 1;
        Request request = new Request(requestID, sender, recipient, requestType, project, RequestStatus.PENDING, body);
        requests.add(request);
        requestDataHandler.saveRequestToDatabase(request);

        return request;
    }

    public void approveRequest(Request request, List<Request> requests) throws IOException {
        request.approve();
        requestDataHandler.saveRequestsToDatabase(requests);
    }

    public void rejectRequest(Request request, List<Request> requests) throws IOException {
        request.reject();
        requestDataHandler.saveRequestsToDatabase(requests);
    }
}