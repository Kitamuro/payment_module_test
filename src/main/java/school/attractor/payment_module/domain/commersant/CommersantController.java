package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.ApacheHttp.Response;
import school.attractor.payment_module.domain.ApacheHttp.SendRequest;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionSearchDTO;
import school.attractor.payment_module.domain.transaction.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
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
        int pageSize = size.orElse ( 10 );
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

    @PostMapping("/sendRequest")
    public String sendRequest(Model model, @RequestParam Integer transactionId, @RequestParam Integer transactionAmount, RedirectAttributes attributes){
        attributes.addFlashAttribute ( "transactionId", transactionId );
        attributes.addFlashAttribute ( "transactionAmount", transactionAmount );
        System.out.println (transactionId );
        return "redirect:/send";
    }

    @GetMapping("/send")
    public String openRequestPage(){
        return "request";
    }

    @GetMapping("/reversePage")
    public String openResponsePage(){
        return "reverseResponse";
    }


    @PostMapping("/confirm")
    public String confirmReverse(@RequestParam Integer transactionId, @RequestParam String transactionAmount, RedirectAttributes attributes){

        Transaction transaction = transactionService.getTransaction(transactionId);
        System.out.println (transactionId );
//        SendRequest sendRequest = new SendRequest ( transaction, transactionAmount, "24" );
//        String responseCode = sendRequest.getResponse ( ).getRcCode ( );
        String  responseCode = "00";
        attributes.addFlashAttribute ( "responseCode", responseCode );
        transaction.setStatus ( "Возвращен" );
        transactionService.change ( transaction );
        System.out.println ("Статус транзакции: " + transaction.getStatus () );
        return "redirect:/reversePage";

//        if (responseCode.equals("00")){
//            List<Response> responses = transaction.getResponses ( );
//            responses.add (sendRequest.getResponse ());
//            transaction.setStatus ( "Возвращен" );
//            return ResponseEntity.status( HttpStatus.OK).body("okay");
//        }else{
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseCode);
//        }

    }


    @GetMapping("/search-result")
    public String getSearchingTransactions(@RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                                           @ModelAttribute("searchKey") TransactionSearchDTO searchDTO, Model model,
                                           @RequestParam(required = false, name = "id") String id,
                                           @RequestParam(required = false, name = "status") String status,
                                           @RequestParam(required = false, name = "shopName") String shopName){
        int currentPage = page.orElse ( 1 );
        int pageSize = size.orElse ( 10 );
        if (page.get() > 1) {
            if (!id.isEmpty()) {
                searchDTO.setId(id);
            }
            if (!status.isEmpty()) {
                searchDTO.setStatus(status);
            }
            if (!shopName.isEmpty()) {
                searchDTO.setShopName(shopName);
            }
        }


        Page<Transaction> transactions = transactionService.searchTransactions ( searchDTO, PageRequest.of(currentPage - 1, pageSize) );
        model.addAttribute ( "transactions", transactions );
        model.addAttribute("searchKey", searchDTO);
        int number = transactions.getNumber ( );
        transactions.getSize ();
        model.addAttribute ( "number", number );
        int totalPages = transactions.getTotalPages ();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed ( 1, totalPages ).boxed ().collect( Collectors.toList());
            model.addAttribute ( "pageNumbers", pageNumbers );
        }

        return "result";
    }

    @PostMapping("/search")
    public String searchTransactions(@RequestParam(required = false, name = "id") String id,
                                     @RequestParam(required = false, name = "status") String status,
                                     @RequestParam(required = false, name = "shopName") String shopName,
                                     RedirectAttributes attributes){
        TransactionSearchDTO searchDTO = new TransactionSearchDTO();
        searchDTO.setId(id);
        searchDTO.setShopName(shopName);
        searchDTO.setStatus(status);
        attributes.addFlashAttribute ( "searchKey", searchDTO );
        return "redirect:/search-result?size=10&page=1";
    }
}

