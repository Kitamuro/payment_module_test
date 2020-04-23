package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@AllArgsConstructor
@RequestMapping
public class CommersantController {

    private final TransactionService transactionService;


    @GetMapping("/")
    public String hello (Model model) {

        return "main";
    }

//    @GetMapping("/transactions")
//    public String transaction(Model model) {
//        model.addAttribute("transactions", GenerateData.addTransaction());
//        return "transactions";
//    }


    @GetMapping("/transactions")
    public String getTransactions(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse ( 1 );
        int pageSize = size.orElse ( 12 );
        Page<Transaction> transactions = transactionService.getTransactions ( PageRequest.of(currentPage - 1, pageSize, Sort.by("shopName").descending() ) );
        model.addAttribute ( "transactions", transactions );
        int number = transactions.getNumber ( );
        transactions.getSize ();
        model.addAttribute ( "number", number );
        int totalPages = transactions.getTotalPages ();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed ( 1, totalPages ).boxed ().collect( Collectors.toList());
            model.addAttribute ( "pageNumbers", pageNumbers );
        }
        return "transactions";
//    https://www.baeldung.com/spring-thymeleaf-pagination
    }
}
