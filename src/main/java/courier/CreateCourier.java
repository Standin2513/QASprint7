package courier;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;

    public CreateCourier(String randomLogin, String randomPassword, String randomFirstName) {
        this.login = randomLogin;
        this.password = randomPassword;
        this.firstName = randomFirstName;
    }

    public CreateCourier() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
}
