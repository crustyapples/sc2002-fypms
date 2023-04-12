package src.Entity;

public enum RequestType {
    REGISTER("Register"),
    CHANGE_TITLE("Change Title"),
    DEREGISTER("Deregister"),
    TRANSFER_STUDENT("Transfer Student");

    private final String label;

    private RequestType(String label) {
        this.label = label;
    }
}
