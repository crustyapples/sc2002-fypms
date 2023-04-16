package src.Controller;

import src.Entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Request data handler.
 */
public class RequestDataHandler implements IRequestDataHandler{
    private static final String REQUEST_FILE = "database/Requests_List.txt";
    /**
     * Load requests from database.
     *
     * @param users the users
     * @param projects the projects
     * @return requests the requests
     * @throws IOException the io exception
     */
    public List<Request> loadRequestsFromDatabase(List<User> users, List<Project> projects) throws IOException {
        List<Request> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUEST_FILE))) {
            String line;
            int requestID = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                String senderUserID = data[0];
                String recipientUserID = data[1];
                String type = data[2];
                String status = data[3];
                String body = data[4];
                int projectID = Integer.parseInt(data[5]);

                User sender = findUserByID(users, senderUserID);
                User recipient = findUserByID(users, recipientUserID);
                Project project = findProjectByID(projects, projectID);

                RequestStatus requestStatus = getRequestStatusEnum(status);
                RequestType requestType = getRequestTypeEnum(type);

                if (sender != null && recipient != null && project != null) {
                    Request request = new Request(requestID, sender, recipient, requestType, project, requestStatus, body);
                    requests.add(request);
                    sender.addRequest(request);
                    recipient.addRequest(request);
                    requestID++;
                }
            }
        }
        return requests;
    }


    public void saveRequestsToDatabase(List<Request> requests) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUEST_FILE))) {
            for (Request request : requests) {
                writer.write(request.getSender().getUserID() + "\t" +
                        request.getRecipient().getUserID() + "\t" +
                        request.getType() + "\t" +
                        request.getStatus() + "\t" +
                        request.getBody() + "\t" +
                        request.getProject().getProjectID());
                writer.newLine();
            }
        }
    }

    public void saveRequestToDatabase(Request request) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUEST_FILE, true))) {
            writer.write(request.getSender().getUserID() + "\t" +
                    request.getRecipient().getUserID() + "\t" +
                    request.getType() + "\t" +
                    request.getStatus() + "\t" +
                    request.getBody() + "\t" +
                    request.getProject().getProjectID());
            writer.newLine();
        }
    }

    private User findUserByID(List<User> users, String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    private Project findProjectByID(List<Project> projects, int projectID) {
        for (Project project : projects) {
            if (project.getProjectID() == projectID) {
                return project;
            }
        }
        return null;
    }

    private static RequestStatus getRequestStatusEnum(String text) {
        switch (text) {
            case "Pending":
                return RequestStatus.PENDING;
            case "Approved":
                return RequestStatus.APPROVED;
            case "Rejected":
                return RequestStatus.REJECTED;
            default:
                return null;
        }
    }

    private static RequestType getRequestTypeEnum(String text) {

        switch (text) {
            case "Register":
                return RequestType.REGISTER;
            case "Change Title":
                return RequestType.CHANGE_TITLE;
            case "Deregister":
                return RequestType.DEREGISTER;
            case "Transfer Student":
                return RequestType.TRANSFER_STUDENT;
            default:
                return null;
        }
    }

}
