package school.attractor.payment_module.domain.order;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public String getOrders(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse ( 1 );
        int pageSize = size.orElse ( 10 );
        Page<Order> orders = orderService.getOrders ( PageRequest.of ( currentPage - 1, pageSize, Sort.by ( "date" ).descending ( ) ) );
        model.addAttribute ( "orders", orders );
        getTotals(model,orderService.getOrders(Pageable.unpaged()));
        addPageNumbers(model, orders);
        return "orders";
    }

    //Predicate is obtained directly out of the HttpRequest, using the @QuerydslPredicate annotation.
    @PostMapping("/search-orders")
    public String findAllSearchOrders(@QuerydslPredicate(root = Order.class ) Predicate predicate, Model model, @RequestParam String id, @RequestParam String shopName, @RequestParam List<Integer> amount, @RequestParam TransactionStatus status){
        Page<Order> orders = orderService.getSearchOrders ( PageRequest.of ( 0, 10, Sort.by ( "date" ).descending ( ) ), predicate );
        getTotals(model,orderService.getSearchOrders(Pageable.unpaged(), predicate));
        addSearchModelAttribute ( model, orders, id, shopName, amount,status );
        return "orders";
   }

    @GetMapping("/search-orders")
    public String findAllSearchOrdersPaginated(@QuerydslPredicate(root = Order.class) Predicate predicate, @RequestParam("page") int page, @RequestParam("size") int size, Model model, @RequestParam String shopName, @RequestParam String id, @RequestParam List<Integer> amount, @RequestParam TransactionStatus status) {
        Page<Order> orders = orderService.getSearchOrders ( PageRequest.of(page-1, size), predicate);
        getTotals(model,orderService.getSearchOrders(Pageable.unpaged(), predicate));
        addSearchModelAttribute ( model, orders,id, shopName, amount,status );
        return "orders";

    }
    private void addSearchModelAttribute(Model model, Page<Order> orders, String id, String shopName, List<Integer> amount, TransactionStatus status) {
        addPageNumbers(model, orders);
        model.addAttribute ( "searchPagination", "true" ).addAttribute ( "orders", orders );
        model.addAttribute ( "page", 0 ).addAttribute ( "size", 10 );
        model.addAttribute ( "searchShopName", shopName ).addAttribute ( "searchId", id ).addAttribute ( "searchStatus", status ).addAttribute ( "searchAmountFrom", amount.get ( 0 ) ).addAttribute ( "searchAmountTo", amount.get ( 1 ) );
    }

    private void addPageNumbers(Model model, Page<Order> orders) {
        int number = orders.getNumber ( );
        model.addAttribute ( "number", number );
        int totalPages = orders.getTotalPages ( );
        if ( totalPages > 0 ) {
            List<Integer> pageNumbers = IntStream.rangeClosed ( 1, totalPages ).boxed ( ).collect ( Collectors.toList ( ) );
            model.addAttribute ( "pageNumbers", pageNumbers );
        }
    }

    private void getTotals(Model model, Page<Order> orders){
        Integer totalAmountSum = orderService.getTotalAmountSum(orders);
        Integer totalQuantity = orderService.getTotalQuantity(orders);
        model.addAttribute("totalAmountSum", totalAmountSum);
        model.addAttribute("totalQuantity", totalQuantity);
    }
}
