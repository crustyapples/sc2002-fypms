package src.Entity;

public class Request {
    private User sender;
    private User recipient;
    private String requestType;
    private String status;
    private Project project;

    public Request(User sender, User recipient, String requestType, Project project, String status) {
        this.sender = sender;
        this.recipient = recipient;
        this.requestType = requestType;
        this.status = status;
        this.project = project;
    }

    public void approve() {
        this.status = "approved";
    }

    public void reject() {
        this.status = "rejected";
    }

    public String viewDetails() {
        return "Request Type: " + requestType + "\nStatus: " + status + "\nSender: " + sender.name + "\nRecipient: " + recipient.name;
    }

    public User getSender() {
        return this.sender;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public String getType() {
        return this.requestType;
    }

    public String getStatus() {
        return this.status;
    }

    public Project getProject() {
        return this.project;
    }

    public void setNewTitle(String newTitle) {
        this.project.setTitle(newTitle);
    }

    public void setNewSupervisor(Supervisor newSupervisor) {
        this.project.setSupervisor(newSupervisor);
    }
}


