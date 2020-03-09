package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import school.attractor.payment_module.domain.transaction.TransactionDTO;



@AllArgsConstructor
@RestController
public class ControllerRest {

    @PostMapping("/pay")
    public HttpStatus mainController(@RequestBody TransactionDTO transactionDTO) {
        System.out.println(transactionDTO);
        return HttpStatus.OK;
    }


}
