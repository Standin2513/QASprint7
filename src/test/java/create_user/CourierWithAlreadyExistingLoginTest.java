package create_user;

import com.github.javafaker.Faker;
import courier.CreateCourier;
import courier.CreateCourierStatus;
import courier.Login;
import courier.LoginStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourierWithAlreadyExistingLoginTest {
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

    @Test
    public void createCourierWithExistingLoginTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(409));
        String expectMessage409 = "Этот логин уже используется. Попробуйте другой.";
        CreateCourier courier = new CreateCourier(login, password, name);
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(expectMessage409, newCourier.getMessage());
    }

    @After
    public void deleteCourierTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Login userLogin = new Login(login, password);
        LoginStatus userLoginCheck = LoginStatus.loginCourierRequest(userLogin);
        Integer id = userLoginCheck.getId();
        LoginStatus.deleteCourier(id);
    }
}
