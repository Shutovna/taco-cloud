package tacos.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entity.TacoOrder;
import tacos.data.OrderRepository;
import tacos.entity.User;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProps props;

    @GetMapping("/currentUser")
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        List<TacoOrder> orders = orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
        model.addAttribute("message", user.getFullName() + " orders");
        model.addAttribute("orders", orders);
        return "orderTable";
    }

    @GetMapping("/all")
    public String allOrders(Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        List<TacoOrder> orders = orderRepository.findAllByOrderByPlacedAtDesc(pageable);
        model.addAttribute("message", "All orders");
        model.addAttribute("orders", orders);
        return "orderTable";
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }


    @PostMapping
    public String processOrder(
            @Valid TacoOrder order, Errors errors,
            SessionStatus sessionStatus, @AuthenticationPrincipal User user
    ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        log.info("Order submitted: " + order);
        orderRepository.save(order);

        sessionStatus.setComplete();

        return "redirect:/";
    }

}