package order;

import static io.restassured.RestAssured.given;

public class SuccessOrder {
    private Integer track;
    private final static String MAKING_ORDER_REQUEST = "/api/v1/orders";

    public SuccessOrder(Integer track) {
        this.track = track;
    }

    public Integer getTrack() {
        return track;
    }

    public static SuccessOrder successOrderRequest(Order successOrder){
        SuccessOrder order = given()
                .body(successOrder)
                .when()
                .post("/api/v1/orders")
                .then().statusCode(201)
                .extract().as(SuccessOrder.class);
        return order;
    }
}
