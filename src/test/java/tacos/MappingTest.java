package tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
@ActiveProfiles("test")
public class MappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void testCreateOrder() {
        TacoOrder order = new TacoOrder();
        order.setDeliveryName("dName");
        order.setDeliveryStreet("dStreet");
        order.setDeliveryCity("dCity");
        order.setDeliveryState("da");
        order.setDeliveryZip("123");
        order.setCcNumber("378282246310005");
        order.setCcExpiration("12/12");
        order.setCcCVV("123");

        Optional<Ingredient> ingredient = ingredientRepository.findById("FLTO");
        Optional<Ingredient> ingredient2 = ingredientRepository.findById("GRBF");
        Optional<Ingredient> ingredient3 = ingredientRepository.findById("CARN");

        Taco taco = new Taco();
        taco.setName("tName");
        taco.addIngredient(ingredient.orElseThrow());

        Taco taco2 = new Taco();
        taco2.setName("tName2");
        taco2.addIngredient(ingredient2.orElseThrow());
        taco2.addIngredient(ingredient3.orElseThrow());

        order.addTaco(taco);
        order.addTaco(taco2);

        orderRepository.save(order);

        Iterator<TacoOrder> orders = orderRepository.findAll().iterator();
        TacoOrder dbOrder = orders.next();
        assertFalse(orders.hasNext());

        List<Taco> tacos = dbOrder.getTacos();
        assertEquals(2, tacos.size());

        assertEquals("tName", tacos.get(0).getName());
        assertEquals(1, tacos.get(0).getIngredients().size());

        assertEquals("tName2", tacos.get(1).getName());
        assertEquals(2, tacos.get(1).getIngredients().size());

        System.out.println("dbOrder = " + dbOrder);
    }
}
