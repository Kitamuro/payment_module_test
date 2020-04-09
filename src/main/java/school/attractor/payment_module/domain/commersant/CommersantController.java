package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
        model.addAttribute("transactions", GenerateData.addTransaction());
        return "transactions";
    }



}
