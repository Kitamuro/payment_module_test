package school.attractor.payment_module.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import school.attractor.payment_module.domain.transaction.TransactionDTO;


@AllArgsConstructor
@NoArgsConstructor
@RestController
public class Controller {

    @PostMapping("/pay")
    public String mainController(TransactionDTO transactionDTO) {

        return HttpStatus.OK.toString();
    }
}
