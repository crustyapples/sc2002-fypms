package src.Entity;

/**
 * The Request class represents a request made by a user for a project,
 * which can be approved or rejected by another user.
 */
public class Request {
    /**
     * The unique identifier of the request.
     */
    private Integer requestID;
    /**
     * The user who sent the request.
     */
    private User sender;
    /**
     * The user who received the request.
     */
    private User recipient;
    /**
     * The type of the request.
     */
    private RequestType requestType;
    /**
     * The status of the request.
     */
    private RequestStatus requestStatus;
    /**
     * The project associated with the request.
     */
    private Project project;
    /**
     * The message body of the request.
     */
    private String body;

    /**
     * Instantiates a new Request object with the specified parameters.
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
     * This method approves the request by setting its status to APPROVED.
     */
    public void approve() {
        this.requestStatus = RequestStatus.APPROVED;
    }

    /**
     * Rejects the request by setting its status to REJECTED.
     */
    public void reject() {
        this.requestStatus = RequestStatus.REJECTED;
    }

    /**
     * Returns a string representation of the request details.
     *
     * @return a string representation of the request details
     */
    public String viewDetails() {
        return "RequestID: " + requestID + "\nRequest Type: " + requestType + "\nStatus: " +  requestStatus + "\nSender: " + sender.getUserID() + "\nRecipient: " + recipient.getUserID() + "\n";
    }

    /**
     * Returns the user who sent the request.
     *
     * @return the user who sent the request
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * Returns the user who received the request.
     *
     * @return the user who received the request
     */
    public User getRecipient() {
        return this.recipient;
    }

    /**
     * Returns the type of the request.
     *
     * @return the type of the request
     */
    public RequestType getType() {
        return this.requestType;
    }

    /**
     * Returns the status of the request.
     *
     * @return the status of the request
     */
    public RequestStatus getStatus() {
        return this.requestStatus;
    }

    /**
     * Returns the project associated with the request.
     *
     * @return the project associated with the request
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Returns the message body of the request.
     *
     * @return the message body of the request
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Returns the unique identifier of the request.
     *
     * @return the unique identifier of the request
     */
    public Integer getRequestID() {
        return this.requestID;
    }
}


