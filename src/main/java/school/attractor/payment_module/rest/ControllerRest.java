package school.attractor.payment_module.rest;



import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import school.attractor.payment_module.domain.ApacheHttp.ResponseService;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.commersant.CommersantRegistrationDataDTO;
import school.attractor.payment_module.domain.commersant.CommersantService;
import school.attractor.payment_module.domain.exception.OrderNotFound;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderDTO;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@CrossOrigin
@AllArgsConstructor
@RestController
public class ControllerRest {

    private final TransactionService transactionService;
    private final ResponseService responseService;
    private final OrderService orderService;
    private final CommersantService commersantService;

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

    @PostMapping("/registration")
    public String  newCommersant(@RequestBody  @Valid CommersantRegistrationDataDTO data,
                                 BindingResult result, HttpServletResponse response) {
        if(result.hasErrors()) {
            StringBuilder errorMessage  = new StringBuilder();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError  e :  errors) {
                errorMessage.append("ERROR: ").append(e.getDefaultMessage());
            }
            return errorMessage.toString();
        } else {

            CommersantDTO commersantDTO = CommersantDTO.from(data);
            commersantService.save(commersantDTO);

            return  "Validation Successful";
        }


    }
}
