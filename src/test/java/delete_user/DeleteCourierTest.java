package delete_user;

import courier.LoginStatus;
import create_user.Specifications;
import org.junit.Test;

public class DeleteCourierTest {
    private final static String URL = "https://qa-scooter.praktikum-services.ru";

    @Test // Удаление пользователя
    public void delete() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        LoginStatus.delete();
    }
}
