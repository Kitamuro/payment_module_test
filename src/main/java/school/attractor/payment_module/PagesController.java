package school.attractor.payment_module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PagesController {

    @GetMapping
    public String orderPage() {
        return "order-page.html";
    }

//
//    @PostMapping("/paymentPage")
//    public String paymentPagePost(OrderDTO order, RedirectAttributes attributes) {
//        attributes.addFlashAttribute("order", order);
//        return "redirect:/paymentPage";
//    }
//
//    @GetMapping("/paymentPage")
//    public String paymentPage() {
//        return "payment-page";
//    }

//    unnecessary
}
