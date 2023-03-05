package Courier;

public class CreateStatus {
    private Boolean ok;
    private String message;

    public CreateStatus(Boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }
}
