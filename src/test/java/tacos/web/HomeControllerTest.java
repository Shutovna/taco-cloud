package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tacos.entity.TacoOrder;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private IngredientRepository ingredientRepository;

    @Test
    @WithMockUser(roles = "USER")
    public void testHomePage() throws Exception {
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setId(1L);
        tacoOrder.setDeliveryName("name1");
        tacoOrder.setDeliveryStreet("street1");
        tacoOrder.setDeliveryCity("city1");
        tacoOrder.setDeliveryState("state1");
        tacoOrder.setDeliveryZip("zip1");
        tacoOrder.setCcNumber("378282246310005");
        tacoOrder.setCcExpiration("12/12");
        tacoOrder.setCcCVV("123");

        when(orderRepository.findAll()).thenReturn(List.of(tacoOrder));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(containsString("Welcome to ...")))
                .andExpect(content().string(containsString(tacoOrder.getDeliveryName())))
                .andExpect(content().string(containsString(tacoOrder.getDeliveryStreet())))
                .andExpect(content().string(containsString(tacoOrder.getDeliveryCity())))
                .andExpect(content().string(containsString(tacoOrder.getDeliveryState())))
                .andExpect(content().string(containsString(tacoOrder.getDeliveryZip())))
                .andExpect(content().string(containsString(tacoOrder.getCcNumber())))
                .andExpect(content().string(containsString(tacoOrder.getCcExpiration())))
                .andExpect(content().string(containsString(tacoOrder.getCcCVV())))
                .andExpect(content().string(containsString(tacoOrder.getPlacedAt().toString())))
        ;
    }
}