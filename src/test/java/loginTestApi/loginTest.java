package loginTestApi;

import Courier.login;
import Courier.loginStatus;
import CreateUser.Specifications;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class loginTest {

    private final static String URL = "https://qa-scooter.praktikum-services.ru";

    @Test // успешный логин
    public void successLoginTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        login userLogin = new login("standin2513", "123321");
        loginStatus userLoginCheck = given()
                .body(userLogin)
                .when()
                .post("/api/v1/courier/login")
                .then().log().all()
                .extract().as(loginStatus.class);
        userLoginCheck.getId();
    }

    @Test // запрос без логина или пароля
    public void LoginWithoutLoginOrPasswordTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        String expected = "Недостаточно данных для входа";
        login userLogin = new login("standin2513", "");
        loginStatus userLoginCheck = given()
                .body(userLogin)
                .when()
                .post("/api/v1/courier/login")
                .then().log().all()
                .extract().as(loginStatus.class);
        Assert.assertEquals(expected, userLoginCheck.getMessage());
}

    @Test // Запрос c несуществующей парой логин-пароль
    public void LoginWithNonExistentCoupleLoginOrPasswordTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(404));
        String expected = "Учетная запись не найдена";
        login userLogin = new login("standin2513", "111");
        loginStatus userLoginCheck = given()
                .body(userLogin)
                .when()
                .post("/api/v1/courier/login")
                .then().log().all()
                .extract().as(loginStatus.class);
        Assert.assertEquals(expected, userLoginCheck.getMessage());

    }
}
