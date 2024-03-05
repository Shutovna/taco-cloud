package tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import com.google.common.collect.Iterables;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class MappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void testFindIngredients() {
        Iterable<Ingredient> all = ingredientRepository.findAll();
        assertEquals(10, Iterables.size(all));

        Ingredient ingredient = all.iterator().next();
        assertEquals("FLTO", ingredient.getId());
        assertEquals("Flour Tortilla", ingredient.getName());
        assertEquals(Ingredient.Type.WRAP, ingredient.getType());

        Ingredient ingredient2 = ingredientRepository.findById("FLTO").orElseThrow();
        assertEquals(ingredient,  ingredient2);
    }
    @Test
    public void testCreateIngredient() {
        Ingredient i = new Ingredient("QWER", "qwer", Ingredient.Type.PROTEIN);
        ingredientRepository.save(i);

        Ingredient dbIngredient = ingredientRepository.findById("QWER").orElseThrow();
        assertEquals(i, dbIngredient);

        i.setName("qwer2");
        ingredientRepository.save(i);
        dbIngredient = ingredientRepository.findById("QWER").orElseThrow();
        assertEquals("qwer2", dbIngredient.getName());
    }

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

        Ingredient ingredient = ingredientRepository.findById("FLTO").orElseThrow();
        Ingredient ingredient2 = ingredientRepository.findById("GRBF").orElseThrow();
        Ingredient ingredient3 = ingredientRepository.findById("CARN").orElseThrow();

        Taco taco = new Taco();
        taco.setName("tName");
        taco.addIngredient(ingredient);

        Taco taco2 = new Taco();
        taco2.setName("tName2");
        taco2.addIngredient(ingredient2);
        taco2.addIngredient(ingredient3);

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
