package src.Entity;

/**
 * The enum Request type.
 */
public enum RequestType implements EnumInterface {
    /**
     * Register request type.
     */
    REGISTER("Register"),
    /**
     * The Change title.
     */
    CHANGE_TITLE("Change Title"),
    /**
     * Deregister request type.
     */
    DEREGISTER("Deregister"),
    /**
     * The Transfer student.
     */
    TRANSFER_STUDENT("Transfer Student");

    private final String label;

    private RequestType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
