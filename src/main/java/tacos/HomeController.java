package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        User u = new User();
        u.setEmail("asd@asd.ri");
        u.setPasword("pwd");

        return "home";
    }
}
