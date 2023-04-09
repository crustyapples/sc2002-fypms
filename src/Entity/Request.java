package src.Entity;

public class Request {
    private Integer requestID;
    private User sender;
    private User recipient;
    private String requestType;
    private String status;
    private Project project;
    private String body;

    public Request(Integer requestID, User sender, User recipient, String requestType, Project project, String status, String body) {
        this.requestID = requestID;
        this.sender = sender;
        this.recipient = recipient;
        this.requestType = requestType;
        this.status = status;
        this.project = project;
        this.body = body;
    }

    public void approve() {
        this.status = "approved";
    }

    public void reject() {
        this.status = "rejected";
    }

    public String viewDetails() {
        return "RequestID: " + requestID + "\nRequest Type: " + requestType + "\nStatus: " + status + "\nSender: " + sender.name + "\nRecipient: " + recipient.name;
    }

    public User getSender() {
        return this.sender;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public String getType() {
        return this.requestType;
    }

    public String getStatus() {
        return this.status;
    }

    public Project getProject() {
        return this.project;
    }

    public String getBody() {
        return this.body;
    }

    public Integer getRequestID() {
        return this.requestID;
    }
}


