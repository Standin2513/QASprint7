package create_user;

import courier.CreateCourier;
import courier.CreateCourierStatus;
import courier.Login;
import courier.LoginStatus;
import org.junit.Assert;
import org.junit.Test;


public class CreateCourierTest {

    private static final String URL = "https://qa-scooter.praktikum-services.ru";

    @Test //Создание нового пользователя
    public void createNewCourierTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        Boolean ok = true;
        CreateCourier courier = new CreateCourier("zxc@zxc.ru", "123321", "Dima");
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(ok, newCourier.getOk());
    }

    @Test // Создание пользователя с повторяющимся логином
    public void createNewCourierSameLoginTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(409));
        String expectMessage409 = "Этот логин уже используется. Попробуйте другой.";
        CreateCourier courier = new CreateCourier("zxc@zxc.ru", "111111", "Diman4ik");
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(expectMessage409, newCourier.getMessage());
    }

    @Test // Создание пользователя без логина или пароля
    public void createNewCourierWithoutLoginOrPasswordTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        String expectMessage400 = "Недостаточно данных для создания учетной записи";
        CreateCourier courier = new CreateCourier("zxc@zxc.ru", "", "Diman4ik");
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(expectMessage400, newCourier.getMessage());
    }
}