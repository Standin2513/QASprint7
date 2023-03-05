package MakingOrder;

import Order.Order;
import Order.SuccessOrder;
import CreateUser.Specifications;
import org.junit.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class MakingOrderTest {

    private final static String URL = "https://qa-scooter.praktikum-services.ru";

    @Test
    public void SuccessOrderWithoutColorsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5,"2023-03-10", "Saske, come back to Konoha", Collections.singletonList("[]"));
        SuccessOrder successOrder = given()
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then().log().all()
                .extract().as(SuccessOrder.class);
        successOrder.getTrack();
    }

    @Test
    public void SuccessOrderWithBlackColorsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5,"2023-03-10", "Saske, come back to Konoha", Collections.singletonList("[BLACK]"));
        SuccessOrder successOrder = given()
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then().log().all()
                .extract().as(SuccessOrder.class);
        successOrder.getTrack();
    }

    @Test
    public void SuccessOrderWithAllColorsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5,"2023-03-10", "Saske, come back to Konoha", Collections.singletonList("[BLACK, GREY]"));
        SuccessOrder successOrder = given()
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then().log().all()
                .extract().as(SuccessOrder.class);
        successOrder.getTrack();
    }
}