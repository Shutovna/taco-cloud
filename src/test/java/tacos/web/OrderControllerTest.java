package tacos.web;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tacos.entity.Ingredient;
import tacos.entity.Taco;
import tacos.entity.TacoOrder;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private IngredientRepository ingredientRepository;
    @MockBean
    private OrderProps orderProps;

    @Test
    @WithMockUser(roles = "USER")
    public void testGetOrderForm() throws Exception {
        when(this.orderRepository.findAll()).thenReturn(List.of());

        MockHttpSession session = new MockHttpSession();

        TacoOrder tacoOrder = new TacoOrder();
        Taco taco = new Taco(
                1L, "name",
                List.of(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.SAUCE)),
                new Date());
        tacoOrder.addTaco(taco
        );
        session.setAttribute("tacoOrder", tacoOrder);


        this.mockMvc.perform(MockMvcRequestBuilders.get("/orders/current").session(session))
                .andExpect(view().name("orderForm"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deliver my taco masterpieces to...")));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testFailCreateOrder() throws Exception {
        MockHttpSession session = new MockHttpSession();

        TacoOrder tacoOrder = new TacoOrder();
        session.setAttribute("tacoOrder", tacoOrder);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/orders").session(session).with(csrf()))
                .andExpect(view().name("orderForm"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Please correct the problems below and resubmit")));

    }
    @Test
    public void testFailCreateOrderWithNoUser() throws Exception {
        MockHttpSession session = new MockHttpSession();

        TacoOrder tacoOrder = new TacoOrder();
        session.setAttribute("tacoOrder", tacoOrder);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/orders").session(session).with(csrf()))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateOrder() throws Exception {
        MockHttpSession session = new MockHttpSession();

        TacoOrder tacoOrder = new TacoOrder();
        session.setAttribute("tacoOrder", tacoOrder);

        tacoOrder.setDeliveryName("dName");
        tacoOrder.setDeliveryStreet("dStreet");
        tacoOrder.setDeliveryCity("dCity");
        tacoOrder.setDeliveryState("da");
        tacoOrder.setDeliveryZip("123");
        tacoOrder.setCcNumber("378282246310005");
        tacoOrder.setCcExpiration("12/12");
        tacoOrder.setCcCVV("123");
        tacoOrder.addTaco(new Taco(1L, "name",
                List.of(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.SAUCE)),
                new Date()));

        MockMvcRequestBuilderUtils.registerPropertyEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));

        when(orderRepository.save(tacoOrder)).thenReturn(tacoOrder);

        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/orders", tacoOrder).session(session).with(csrf()))
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());

    }
}