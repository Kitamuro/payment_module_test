package school.attractor.payment_module.rest;



import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import school.attractor.payment_module.domain.ApacheHttp.ResponseService;
import school.attractor.payment_module.domain.exception.OrderNotFound;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderDTO;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DateFormatter;
import javax.validation.Valid;
import java.io.IOException;

import java.lang.reflect.Executable;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.Optional;


@CrossOrigin
@AllArgsConstructor
@RestController
public class ControllerRest {

    private final TransactionService transactionService;
    private final ResponseService responseService;
    private final OrderService orderService;

    @PostMapping("/pay")
    public ResponseEntity mainController(@Valid @RequestBody OrderDTO orderDTO) throws IOException {
        Order order = orderService.save ( orderDTO );
        Transaction transaction = transactionService.makeTransaction ( order, orderDTO.getAmount (), orderDTO.getType () );
        String trStatus = responseService.sendRequest(transaction);
        order.getTransactions ().add(transaction);
        orderService.change ( order );
        if (trStatus.equals ( "SUCCESS" )){
            return ResponseEntity.status(HttpStatus.OK).body("okay");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NOT OK");
        }
    }

    @GetMapping("/orders/{id}")
    public OrderDTO transactionData(@PathVariable Integer id) {
        try {
            OrderDTO order = orderService.findByOrderId(id);
            order.setCard ("1111 **** **** 1111");
            return order;
        } catch (OrderNotFound e) {
            return null;
        }
    }
}
