package src.Entity;

/**
 * The enum ProjectStatus represents the different statuses that a project can have.
 */
public enum ProjectStatus implements EnumInterface {
    /**
     * Available project status. The project is available..
     */
    AVAILABLE("Available"),
    /**
     * Reserved project status. The project is reserved.
     */
    RESERVED("Reserved"),
    /**
     * Unavailable project status. The project is unavailable.
     */
    UNAVAILABLE("Unavailable"),
    /**
     * Allocated project status. The project is allocated.
     */
    ALLOCATED("Allocated");

    private final String label;

    /**

     Constructs a new ProjectStatus with the given label.
     @param label the label for the ProjectStatus
     */
    private ProjectStatus(String label) {
        this.label = label;
    }
    /**
     Returns the label for this ProjectStatus.
     @return the label for this ProjectStatus
     */
    @Override
    public String toString() {
        return this.label;
    }
}
