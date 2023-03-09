package delete_user;

import courier.Login;
import courier.LoginStatus;
import create_user.Specifications;
import org.junit.Test;

public class DeleteCourierTest {
    private final static String URL = "https://qa-scooter.praktikum-services.ru";

    @Test // Удаление пользователя
    public void delete() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Login userLogin = new Login("zxc@zxc.ru", "123321");
        LoginStatus userLoginCheck = LoginStatus.loginCourierRequest(userLogin);
        Integer id = userLoginCheck.getId();
        LoginStatus.deleteCourier(id);
    }
}
