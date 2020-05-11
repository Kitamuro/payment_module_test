package school.attractor.payment_module.rest;



import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.attractor.payment_module.domain.ApacheHttp.ResponseService;
import school.attractor.payment_module.domain.exception.OrderNotFound;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderDTO;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionService;

import javax.validation.Valid;


@CrossOrigin
@AllArgsConstructor
@RestController
public class ControllerRest {

    private final TransactionService transactionService;
    private final ResponseService responseService;
    private final OrderService orderService;

    @PostMapping("/pay")
    public ResponseEntity mainController(@RequestBody OrderDTO orderDTO) {
        System.out.println(orderDTO);
        Order order = orderService.save ( orderDTO );
        Transaction transaction = transactionService.makeTransaction ( order, orderDTO.getAmount (), orderDTO.getType () );
        String trStatus = responseService.sendRequest(transaction);
        order.getTransactions ().add(transaction);
        orderService.change ( order );
        if (trStatus.equals ( "SUCCESS" )){
            return ResponseEntity.status(HttpStatus.OK).body("okay");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT OK");
        }
    }

    @GetMapping("/orders/{id}")
    public OrderDTO transactionData(@PathVariable Integer id) {
        try {
            return orderService.findByOrderId(id);
        } catch (OrderNotFound e) {
            return null;
        }
    }
}
