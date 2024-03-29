package courier;

import static io.restassured.RestAssured.given;

public class LoginStatus {
    private Integer id;
    private String message;
    private final static String LOGIN_REQUEST = "/api/v1/courier/login";
    private static final String DELETE_COURIER_REQUEST = "/api/v1/courier/";
    public LoginStatus(Integer id) {
        this.id = id;
    }
    public  Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public static LoginStatus loginCourierRequest(Login userLogin){
        LoginStatus userLoginCheck = given()
                .body(userLogin)
                .when()
                .post(LOGIN_REQUEST)
                .then().log().all()
                .extract().as(LoginStatus.class);
        return userLoginCheck;
    }

    public static void deleteCourier(Integer id) {
        given()
                .when()
                .delete(DELETE_COURIER_REQUEST+id)
                .then().log().all();
    }
}
