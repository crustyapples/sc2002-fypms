package src.Controller;

import src.Entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDataHandler {
    private static final String REQUEST_FILE = "database/Requests_List.txt";

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
                RequestStatus requestStatus = RequestStatus.valueOf(data[3]);
                String body = data[4];
                int projectID = Integer.parseInt(data[5]);

                User sender = findUserByID(users, senderUserID);
                User recipient = findUserByID(users, recipientUserID);

                Project project = findProjectByID(projects, projectID);

                if (sender != null && recipient != null && project != null) {
                    Request request = new Request(requestID, sender, recipient, type, project, requestStatus, body);
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

}
