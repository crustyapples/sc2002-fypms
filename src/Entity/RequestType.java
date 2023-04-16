package src.Entity;

/**
 * The RequestType enum represents the type of request.
 * It implements the EnumInterface interface.
 */
public enum RequestType implements EnumInterface {
    /**
     * A request to register a course.
     */
    REGISTER("Register"),
    /**
     * A request to change the title of a course.
     */
    CHANGE_TITLE("Change Title"),
    /**
     * A request to transfer a student to another course.
     */
    DEREGISTER("Deregister"),
    /**
     * The label of the request type.
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
