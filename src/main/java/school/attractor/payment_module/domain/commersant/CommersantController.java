package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.ApacheHttp.ResponseService;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@AllArgsConstructor
@RequestMapping
public class CommersantController {

    private final TransactionService transactionService;
    private final OrderService orderService;
    private final ResponseService responseService;
    private final CommersantService commersantService;

    @GetMapping("/")
    public String hello() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model){
        model.addAttribute ( "error", error );
        return "login";
    }

    @GetMapping("/register")
    public  String registerPage(Model model){
        if(!model.containsAttribute ( "commersant" )){
            model.addAttribute ( "commersant", new CommersantRegistrationDataDTO () );
        }
        return "registration";
    }

    @PostMapping("/register")
    public String registration(@Valid CommersantRegistrationDataDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute ( "commersant", data );
        if(bindingResult.hasFieldErrors (  )){
            redirectAttributes.addFlashAttribute ( "errors", bindingResult.getFieldErrors ()  );
            return "redirect:/register";
        }
        try {
            commersantService.register(data);
        } catch (CommersantAlreadyRegisteredException uarEx) {
            redirectAttributes.addFlashAttribute ("message", "Commersant already exists.");
            return "redirect:/register";
        }
        return "redirect:/login";
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

