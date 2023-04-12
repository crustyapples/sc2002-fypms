package src.Entity;

public enum ProjectStatus {
    AVAILABLE("Available"),
    RESERVED("Reserved"),
    UNAVAILABLE("Unavailable"),
    ALLOCATED("Allocated");

    private final String label;

    private ProjectStatus(String label) {
        this.label = label;
    }
}
