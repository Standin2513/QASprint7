package courier;

import static io.restassured.RestAssured.given;

public class CreateCourierStatus {
    private static final String CREATE_COURIER_REQUEST = "/api/v1/courier";
    private Boolean ok;
    private String message;

    public CreateCourierStatus(Boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public CreateCourierStatus() {
    }

    public Boolean getOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public static CreateCourierStatus createCourierRequest(CreateCourier courier) {
        CreateCourierStatus newCourier = given()
                .body(courier)
                .when()
                .post(CREATE_COURIER_REQUEST)
                .then().log().all()
                .extract().as(CreateCourierStatus.class);
        return newCourier;
    }
}
