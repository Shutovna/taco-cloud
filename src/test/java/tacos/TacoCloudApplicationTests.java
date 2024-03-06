package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.web.HomeController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TacoCloudApplicationTests {
    @Autowired
    private HomeController controller;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(ingredientRepository).isNotNull();
        assertThat(orderRepository).isNotNull();
    }
}
