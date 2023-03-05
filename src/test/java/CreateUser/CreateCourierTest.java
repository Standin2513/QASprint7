package CreateUser;

import Courier.CreateStatus;
import Courier.createCourier;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateCourierTest {

    private static final String URL = "https://qa-scooter.praktikum-services.ru";

    @Test //Создание нового пользователя
    public void CreateNewCourierTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Boolean ok = true;
        createCourier courier = new createCourier("standin2513", "123321", "Dima");
        CreateStatus newCourier = given()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .extract().as(CreateStatus.class);
        Assert.assertEquals(ok, newCourier.getOk());
    }

    @Test // Создание пользователя с повторяющимся логином
    public void CreateNewCourierSameLoginTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(409));
        String expectMessage409 = "Этот логин уже используется. Попробуйте другой.";
        createCourier courier = new createCourier("standin2513", "111111", "Diman4ik");
        CreateStatus newCourier = given()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .extract().as(CreateStatus.class);
        Assert.assertEquals(expectMessage409, newCourier.getMessage());
    }

    @Test // Создание пользователя без логина или пароля
    public void CreateNewCourierWithoutLoginOrPasswordTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        String expectMessage400 = "Недостаточно данных для создания учетной записи";
        createCourier courier = new createCourier("standin2513", "", "Diman4ik");
        CreateStatus newCourier = given()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .extract().as(CreateStatus.class);
        Assert.assertEquals(expectMessage400, newCourier.getMessage());
    }

//    @Test // Создание пользователя без логина или пароля
//    public void delete() {
//        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
//        Boolean ok = true;
//        given()
//                .when()
//                .delete("/api/v1/courier/162463")
//                .then().log().all();
//
//   }
}
