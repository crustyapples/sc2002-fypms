package src.Entity;

public enum ProjectStatus implements EnumInterface {
    AVAILABLE("Available"),
    RESERVED("Reserved"),
    UNAVAILABLE("Unavailable"),
    ALLOCATED("Allocated");

    private final String label;

    private ProjectStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
