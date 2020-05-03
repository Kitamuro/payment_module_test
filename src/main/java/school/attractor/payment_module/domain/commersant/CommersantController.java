package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.transaction.*;

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
        Page<Transaction> transactions = transactionService.getTransactions ( PageRequest.of(currentPage - 1, pageSize, Sort.by("date").descending() ) );

        model.addAttribute ( "transactions", transactions );
        int number = transactions.getNumber ( );

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
    public String sendRequest(Model model, @RequestParam String orderId, @RequestParam Integer transactionAmount,
                             @RequestParam String shopName, RedirectAttributes attributes){
        attributes.addFlashAttribute("shopName", shopName);
        attributes.addFlashAttribute ( "orderId", orderId );
        attributes.addFlashAttribute ( "transactionAmount", transactionAmount );
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
    public String confirmReverse(@RequestParam String orderId, @RequestParam int transactionAmount,
                                @RequestParam String shopName, RedirectAttributes attributes){

       int sum = transactionService.getSum(orderId);

       System.out.println(sum);

        Transaction refund = Transaction.builder()
                .orderId(orderId)
                .amount(-transactionAmount)
                .shopName(shopName)
                .type(TransactionType.REFUND)
                .status(TransactionStatus.NEW)
                .date(new Date())
                .build();
        transactionService.save(TransactionDTO.from(refund));

        attributes.addFlashAttribute ( "responseCode", "00" );

//        Transaction transaction = transactionService.getTransaction(transactionId);
//        System.out.println (transactionId );
////        SendRequest sendRequest = new SendRequest ( transaction, transactionAmount, "24" );
////        String responseCode = sendRequest.getResponse ( ).getRcCode ( );
//        String  responseCode = "00";
//        attributes.addFlashAttribute ( "responseCode", responseCode );
//        transaction.setStatus ( "Возвращен" );
//        transactionService.change ( transaction );
//        System.out.println ("Статус транзакции: " + transaction.getStatus () );


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

