package src.Entity;

/**
 * The enum Request status.
 */
public enum RequestStatus implements EnumInterface {
    /**
     * Pending request status.
     */
    PENDING("Pending"),
    /**
     * Approved request status.
     */
    APPROVED("Approved"),
    /**
     * Rejected request status.
     */
    REJECTED("Rejected");
    private final String label;

    private RequestStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
