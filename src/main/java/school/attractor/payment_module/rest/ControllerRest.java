package school.attractor.payment_module.rest;



import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import school.attractor.payment_module.domain.exception.OrderNotFound;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderDTO;
import school.attractor.payment_module.domain.order.OrderService;
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

    TransactionService transactionService;
    OrderService orderService;

    @PostMapping("/pay")
    public ResponseEntity mainController(@Valid @RequestBody TransactionDTO transactionDTO, HttpServletRequest request) throws IOException {
        System.out.println(transactionDTO);
//        Random random = new Random();
//        String orderId = String.valueOf ( random.nextInt ( 999999 ) + 100000 );
//        ApacheHttpClientPost apacheHttpClientPost = new ApacheHttpClientPost ( transactionDTO.getCARD ( ), transactionDTO.getEXP ( ), transactionDTO.getEXP_YEAR ( ), transactionDTO.getCVC2 ( ), transactionDTO.getAmount ( ), orderId );
//        String responseCode = apacheHttpClientPost.getResponseDTO ().getRcCode ();
//        orderService.create();
//        orderService.save();
        transactionService.save(transactionDTO);
//
//
        return ResponseEntity.status(HttpStatus.OK).body("okay");
//        if (responseCode.equals("00")){
//            transactionDTO.setResponseDTO ( apacheHttpClientPost.getResponseDTO ());
//            return ResponseEntity.status(HttpStatus.OK).body("okay");
//        }else{
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseCode);
//        }
//            return  ResponseEntity.status(HttpStatus.CONFLICT).body("not okay?");
    }

    @GetMapping("/orders/{id}")
    public OrderDTO transactionData(@PathVariable Integer id) {
        try {
            OrderDTO order = orderService.findByOrderId(id);
            order.setCARD("1111 **** **** 1111");
            return order;
        } catch (OrderNotFound e) {
            return null;
        }
    }
}
