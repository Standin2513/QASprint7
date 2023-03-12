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

public class CreateCourierTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru";
    Faker faker = new Faker();
    String login = faker.name().username();
    String password = faker.pokemon().name();
    String name = faker.name().firstName();

    @Before
    public void createCourier(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Boolean ok = true;
        CreateCourier courier = new CreateCourier(login, password, name);
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(ok, newCourier.getOk());
        System.out.println("Курьер создан.");
    }

    @Test
    public void createCourierWithExistingLoginTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(409));
        String expectMessage409 = "Этот логин уже используется. Попробуйте другой.";
        CreateCourier courier = new CreateCourier(login, password, name);
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(expectMessage409, newCourier.getMessage());
    }

    @Test // Создание пользователя без логина или пароля
    public void createNewCourierWithoutLoginOrPasswordTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        String expectMessage400 = "Недостаточно данных для создания учетной записи";
        CreateCourier courier = new CreateCourier(login, "", name);
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(expectMessage400, newCourier.getMessage());
        System.out.println("Курьер не создан. Укажи пароль.");
    }

    @After
    public void deleteCourier(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Login userLogin = new Login(login, password);
        LoginStatus userLoginCheck = LoginStatus.loginCourierRequest(userLogin);
        Integer id = userLoginCheck.getId();
        LoginStatus.deleteCourier(id);
        System.out.println("Курьер удален.");
    }
}
