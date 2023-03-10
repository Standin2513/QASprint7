package login_test_api;

import courier.Login;
import courier.LoginStatus;
import create_user.Specifications;
import org.junit.Assert;
import org.junit.Test;

public class LoginWithNonExistentCredentialsTest {
    private final static String URL = "https://qa-scooter.praktikum-services.ru";

    @Test // Запрос c несуществующей парой логин-пароль
    public void loginWithNonExistentCoupleLoginOrPasswordTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(404));
        String expected = "Учетная запись не найдена";
        Login userLogin = new Login("zxc@zxc.ru", "123321");
        LoginStatus userLoginCheck = LoginStatus.loginCourierRequest(userLogin);
        Assert.assertEquals(expected, userLoginCheck.getMessage());
    }
}
