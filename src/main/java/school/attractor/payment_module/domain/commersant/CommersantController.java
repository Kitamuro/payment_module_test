package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.ApacheHttp.ResponseService;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderDTO;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@AllArgsConstructor
@RequestMapping
public class CommersantController {

    private final TransactionService transactionService;
    private final OrderService orderService;
    private final ResponseService responseService;

    @GetMapping("/")
    public String hello(Model model, Principal principal) {
        if(principal !=null){
            model.addAttribute ( "user", principal.getName () );
        }
        return "main";
    }


    @PostMapping("/sendRequest")
    public String sendRequest(Model model, @RequestParam int orderId, @RequestParam int amount, @RequestParam String type,
                              RedirectAttributes attributes) {
        attributes.addFlashAttribute ( "type", type );
        attributes.addFlashAttribute ( "orderId", orderId );
        attributes.addFlashAttribute ( "amount", amount );
        return "redirect:/send";
    }

    @GetMapping("/send")
    public String openRequestPage() {
        return "request";
    }

    @GetMapping("/reversePage")
    public String openResponsePage() {
        return "reverseResponse";
    }


    @PostMapping("/confirm")
    public String confirmReverse(@RequestParam int orderId, @RequestParam int amount, @RequestParam TransactionType type,
                                 RedirectAttributes attributes) {
        Order order = orderService.findById ( orderId );
        Transaction transaction = transactionService.makeTransaction ( order, amount, type );
        String trStatus = responseService.sendRequest ( transaction);
        order.getTransactions ().add(transaction);
        order.setResidual( order.getResidual() - amount);
        orderService.change ( order );
        if (trStatus.equals ( "SUCCESS" )) {
            attributes.addFlashAttribute ( "response", "SUCCESS" );
        } else {
            attributes.addFlashAttribute ( "response", "REFUSED" );
        }
        return "redirect:/reversePage";
    }

}

