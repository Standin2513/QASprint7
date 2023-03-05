package Courier;

public class loginStatus {
    private Integer id;
    private String message;

    public loginStatus(Integer id) {
        this.id = id;
    }

    public  Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
