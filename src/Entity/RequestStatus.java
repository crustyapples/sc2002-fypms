package src.Entity;

/**
 * The RequestStatus enum represents the status of a request.
 */
public enum RequestStatus implements EnumInterface {
    /**
     * The request is currently pending approval.
     */
    PENDING("Pending"),
    /**
     * The request has been approved.
     */
    APPROVED("Approved"),
    /**
     * The request has been rejected.
     */
    REJECTED("Rejected");
    /**
     * The label of the status.
     */
    private final String label;
    /**
     * Constructor for RequestStatus enum.
     * @param label the label of the status.
     */
    private RequestStatus(String label) {
        this.label = label;
    }
    /**
     * Returns the label of the status.
     * @return the label of the status.
     */
    @Override
    public String toString() {
        return this.label;
    }
}
