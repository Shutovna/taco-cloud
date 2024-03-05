package tacos.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest()
public class ErrorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testErrorPage() throws Exception {
        mockMvc.perform(get("/qwerty"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(content().string(containsString("Something went wrong!")));
    }
}
