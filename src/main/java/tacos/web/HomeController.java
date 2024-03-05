package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.OrderRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute
    public void addOrdersToOrder(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
    }

    @GetMapping
    public String home() {
        return "home";
    }
}
