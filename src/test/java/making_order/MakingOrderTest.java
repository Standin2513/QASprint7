package making_order;

import order.Order;
import order.SuccessOrder;
import create_user.Specifications;
import org.junit.Test;
import java.util.Collections;

public class MakingOrderTest {
    private final static String URL = "https://qa-scooter.praktikum-services.ru";
    String[] colors = {
            "",
            "BLACK",
            "GREY",
            "BLACK, GREY",
    };

    @Test
    public void test(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(201));
        for(int i =0; i <= colors.length - 1; i++){
            Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5,"2023-03-10", "Saske, come back to Konoha", Collections.singletonList(colors[i]));
            SuccessOrder successOrder = SuccessOrder.successOrderRequest(order);
            successOrder.getTrack();
        }
    }
}