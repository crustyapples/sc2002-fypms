package src.Entity;

public class Request {
    private Integer requestID;
    private User sender;
    private User recipient;
    private String requestType;
    private String requestStatus;
    private Project project;
    private String body;

    public Request(Integer requestID, User sender, User recipient, String requestType, Project project, String requestStatus, String body) {
        this.requestID = requestID;
        this.sender = sender;
        this.recipient = recipient;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
        this.project = project;
        this.body = body;
    }

    public void approve() {
        this.requestStatus = RequestStatus.APPROVED.toString();
    }

    public void reject() {
        this.requestStatus = RequestStatus.REJECTED.toString();
    }

    public String viewDetails() {
        return "RequestID: " + requestID + "\nRequest Type: " + requestType + "\nStatus: " + requestStatus + "\nSender: " + sender.name + "\nRecipient: " + recipient.name;
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
        return this.requestStatus;
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


