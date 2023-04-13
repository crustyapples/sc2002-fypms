package src.Entity;

public enum RequestStatus implements EnumInterface {
    PENDING("Pending"),
    APPROVED("Approved"),
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
