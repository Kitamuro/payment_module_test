package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.attractor.payment_module.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping
public class CommersantController {


    @GetMapping("/")
    public String hello (Model model) {

        return "main";
    }

    @GetMapping("/transactions")
    public String transaction(Model model) {

        Transaction test = Transaction.builder()
                .amount(100)
                .CARD("333-333-333-333-333")
                .cardHolderName("Test Name")
                .CVC2("333")
                .EXP("20/20")
                .email("test@test")
                .userName("test")
                .build();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add( test);
        transactions.add( Transaction.builder().userName("test2").build());

        model.addAttribute("transactions", transactions);
        return "transactions";
    }

}
