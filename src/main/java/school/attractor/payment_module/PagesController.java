package school.attractor.payment_module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.order.OrderDTO;

@Controller
@RequestMapping("/")
public class PagesController {

    @GetMapping
    public String orderPage() {
        return "order-page";
    }


    @PostMapping("/paymentPage")
    public String paymentPagePost(OrderDTO order, RedirectAttributes attributes) {
        attributes.addFlashAttribute("order", order);
        return "redirect:/paymentPage";
    }

    @GetMapping("/paymentPage")
    public String paymentPage() {
        return "payment-page";
    }

}
