package login_test_api;

import com.github.javafaker.Faker;
import courier.CreateCourier;
import courier.CreateCourierStatus;
import courier.Login;
import courier.LoginStatus;
import create_user.Specifications;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginWithoutAnyDataTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru";
    Faker faker = new Faker();
    String login = faker.name().username();
    String password = faker.pokemon().name();
    String name = faker.name().firstName();

    @Before
    public void createCourierTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Boolean ok = true;
        CreateCourier courier = new CreateCourier(login, password, name);
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(ok, newCourier.getOk());
    }

    @Test // запрос без логина или пароля
    public void loginWithoutLoginOrPasswordTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        String expected = "Недостаточно данных для входа";
        Login userLogin = new Login(login, "");
        LoginStatus userLoginCheck = LoginStatus.loginCourierRequest(userLogin);
        Assert.assertEquals(expected, userLoginCheck.getMessage());
    }

    @After
    public void deleteCourierTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Login userLogin = new Login(login, password);
        LoginStatus userLoginCheck = LoginStatus.loginCourierRequest(userLogin);
        Integer id = userLoginCheck.getId();
        LoginStatus.deleteCourier(id);
        System.out.println("Курьер с айди: " + id + " удален.");
    }
}
