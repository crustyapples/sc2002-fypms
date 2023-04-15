package src.Controller;

import src.Entity.Project;
import src.Entity.Request;
import src.Entity.RequestType;
import src.Entity.User;

import java.io.IOException;
import java.util.List;

public interface IRequestController {
    Request createRequest(User sender, User recipient, RequestType requestType, Project project, String body, List<Request> requests) throws IOException;
    void approveRequest(Request request, List<Request> requests) throws IOException;
    void rejectRequest(Request request, List<Request> requests) throws IOException;
}
