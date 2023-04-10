package src.Entity;

public enum RequestStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");
    private final String label;

    private RequestStatus(String label) {
        this.label = label;
    }
}
