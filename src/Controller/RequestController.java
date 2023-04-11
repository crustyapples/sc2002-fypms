package src.Controller;

import src.Entity.Project;
import src.Entity.Request;
import src.Entity.RequestStatus;
import src.Entity.User;

import java.io.IOException;
import java.util.List;

public class RequestController {
    private RequestDataHandler requestDataHandler;

    public RequestController() throws IOException {
        requestDataHandler = new RequestDataHandler();
    }

    public Request createRequest(User sender, User recipient, String requestType, Project project, String body, List<Request> requests) throws IOException {
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