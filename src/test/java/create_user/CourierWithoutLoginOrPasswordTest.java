package create_user;

import com.github.javafaker.Faker;
import courier.CreateCourier;
import courier.CreateCourierStatus;
import org.junit.Assert;
import org.junit.Test;

public class CourierWithoutLoginOrPasswordTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru";

    Faker faker = new Faker();
    String login = faker.name().username();
    String name = faker.name().firstName();


    @Test // Создание пользователя без логина или пароля
    public void createNewCourierWithoutLoginOrPasswordTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        String expectMessage400 = "Недостаточно данных для создания учетной записи";
        CreateCourier courier = new CreateCourier(login, "", name);
        CreateCourierStatus newCourier = CreateCourierStatus.createCourierRequest(courier);
        Assert.assertEquals(expectMessage400, newCourier.getMessage());
    }

}
