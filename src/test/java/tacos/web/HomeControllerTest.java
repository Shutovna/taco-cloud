package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.web.HomeController;
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
    public void testHomePage() throws Exception {
        TacoOrder tacoOrder = new TacoOrder(
                1L, "name1", "street1", "city1",
                "state1", "zip1", "378282246310005",
                "12/12", "123", new Date(), new ArrayList<>()
        );
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
                .andExpect(content().string(not(tacoOrder.getPlacedAt().toString())))
        ;
    }
}