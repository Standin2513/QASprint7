package making_order;

import order.Order;
import order.SuccessOrder;
import create_user.Specifications;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
public class MakeOrderTest {
    private final static String URL = "https://qa-scooter.praktikum-services.ru";
    private String color;
    public MakeOrderTest(String color){
        this.color = color;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> listOfColors() {
        Object[][] color = new Object[][]{
                {"BLACk"},
                {"GREY"},
                {""},
                {"BLACK, GREY"}
        };
        return Arrays.asList(color);
    }

    @Test
    public void createOrderWithDifferentColors(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
            Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5,"2023-03-10", "Saske, come back to Konoha", Collections.singletonList(color));
            SuccessOrder successOrder = SuccessOrder.successOrderRequest(order);
            Integer track = successOrder.getTrack();
            Assert.assertNotNull(track);
            System.out.println(track);
    }
}