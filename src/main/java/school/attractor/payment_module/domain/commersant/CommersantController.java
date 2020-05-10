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
    public String hello(Model model) {
        return "main";
    }

//    @GetMapping("/transactions")
//    public String transaction(Model model) {
//        model.addAttribute("transactions", GenerateData.addTransaction());
//        return "transactions";
//    }

    @GetMapping("/orders")
    public String getTransactions(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Order> orders = orderService.getOrders(PageRequest.of(currentPage - 1, pageSize, Sort.by("date").descending()));
        model.addAttribute("orders", orders);
        int number = orders.getNumber();

        model.addAttribute("number", number);
        int totalPages = orders.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "orders";
    }

    @PostMapping("/sendRequest")
    public String sendRequest(Model model, @RequestParam int orderId, @RequestParam int refundAmount, @RequestParam String type,
                              RedirectAttributes attributes) {
        attributes.addFlashAttribute ( "type", type );
        attributes.addFlashAttribute ( "orderId", orderId );
        attributes.addFlashAttribute ( "refundAmount", refundAmount );
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
    public String confirmReverse(@RequestParam int orderId, @RequestParam int refundAmount, @RequestParam String type,
                                 RedirectAttributes attributes) {
        Order order = orderService.findById ( orderId );
        TransactionType trType;
        if(type.equals ( "0" )){
            trType = TransactionType.REFUND;
        }else{
            trType = TransactionType.AUTH;
        }
        Transaction transaction = transactionService.makeTransaction ( order, refundAmount, trType );
        String trStatus = responseService.sendRequest ( transaction);
        order.getTransactions ().add(transaction);
        orderService.change ( order );
        if (trStatus.equals ( "SUCCESS" )) {
            attributes.addFlashAttribute ( "response", "SUCCESS" );
        } else {
            attributes.addFlashAttribute ( "response", "REFUSED" );
        }
        return "redirect:/reversePage";
    }

//    @GetMapping("/search-result")
//    public String getSearchingTransactions(@RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
//                                           @ModelAttribute("searchKey") TransactionSearchDTO searchDTO, Model model,
//                                           @RequestParam(required = false, name = "id") String id,
//                                           @RequestParam(required = false, name = "amount")  Integer amount,
//                                           @RequestParam(required = false, name = "shopName") String shopName){
//        int currentPage = page.orElse ( 1 );
//        int pageSize = size.orElse ( 10 );
//        if (page.get() > 1) {
//            if (id != null) {
//                searchDTO.setId(id);
//            }
//            if (amount != null) {
//                searchDTO.setAmount(amount);
//            }
//            if (shopName != null) {
//                searchDTO.setShopName(shopName);
//            }
////            if (amount != null){
////                searchDTO.setAmount(amount);
////            }
//        }
//
//        Page<Transaction> transactions = transactionService.searchTransactions ( searchDTO);
//        model.addAttribute ( "transactions", transactions );
//        model.addAttribute("searchKey", searchDTO);
//        int number = transactions.getNumber ( );
//        transactions.getSize ();
//        model.addAttribute ( "number", number );
//        int totalPages = transactions.getTotalPages ();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed ( 1, totalPages ).boxed ().collect( Collectors.toList());
//            model.addAttribute ( "pageNumbers", pageNumbers );
//        }
//
//        return "result";
//    }

    @PostMapping("/search")
    public String searchTransactions(@RequestParam(required = false, name = "id") String id,
                                     @RequestParam(required = false, name = "amount") Integer amount,
                                     @RequestParam(required = false, name = "shopName") String shopName,
                                     RedirectAttributes attributes) {
        TransactionSearchDTO searchDTO = new TransactionSearchDTO();

        searchDTO.setId(id);
        searchDTO.setShopName(shopName);
        searchDTO.setAmount(amount);
        System.out.println(id);
        System.out.println(amount);
        System.out.println(shopName);

        attributes.addFlashAttribute("searchKey", searchDTO);
        return "redirect:/search-result?size=10&page=1";
    }
}

