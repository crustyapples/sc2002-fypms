package src.Entity;

/**
 * The type Request.
 */
public class Request {
    private Integer requestID;
    private User sender;
    private User recipient;
    private RequestType requestType;
    private RequestStatus requestStatus;
    private Project project;
    private String body;

    /**
     * Instantiates a new Request.
     *
     * @param requestID     the request id
     * @param sender        the sender
     * @param recipient     the recipient
     * @param requestType   the request type
     * @param project       the project
     * @param requestStatus the request status
     * @param body          the body
     */
    public Request(Integer requestID, User sender, User recipient, RequestType requestType, Project project, RequestStatus requestStatus, String body) {
        this.requestID = requestID;
        this.sender = sender;
        this.recipient = recipient;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
        this.project = project;
        this.body = body;
    }

    /**
     * Approve.
     */
    public void approve() {
        this.requestStatus = RequestStatus.APPROVED;
    }

    /**
     * Reject.
     */
    public void reject() {
        this.requestStatus = RequestStatus.REJECTED;
    }

    /**
     * View details string.
     *
     * @return the string
     */
    public String viewDetails() {
        return "RequestID: " + requestID + "\nRequest Type: " + requestType + "\nStatus: " +  requestStatus + "\nSender: " + sender.getUserID() + "\nRecipient: " + recipient.getUserID() + "\n";
    }

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * Gets recipient.
     *
     * @return the recipient
     */
    public User getRecipient() {
        return this.recipient;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public RequestType getType() {
        return this.requestType;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public RequestStatus getStatus() {
        return this.requestStatus;
    }

    /**
     * Gets project.
     *
     * @return the project
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Gets request id.
     *
     * @return the request id
     */
    public Integer getRequestID() {
        return this.requestID;
    }
}


