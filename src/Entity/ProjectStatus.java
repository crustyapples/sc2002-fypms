package src.Entity;

/**
 * The enum Project status.
 */
public enum ProjectStatus implements EnumInterface {
    /**
     * Available project status.
     */
    AVAILABLE("Available"),
    /**
     * Reserved project status.
     */
    RESERVED("Reserved"),
    /**
     * Unavailable project status.
     */
    UNAVAILABLE("Unavailable"),
    /**
     * Allocated project status.
     */
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
