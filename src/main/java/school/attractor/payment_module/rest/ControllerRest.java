package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import java.io.IOException;


@AllArgsConstructor
@RestController
public class ControllerRest {

    @PostMapping("/pay")
    public HttpStatus mainController(@RequestBody TransactionDTO transactionDTO) throws IOException {
        System.out.println(transactionDTO);
        ApacheHttpClientPost.sendRequest (transactionDTO.getCARD (), transactionDTO.getEXP (), transactionDTO.getEXP_YEAR (), transactionDTO.getCVC2 ());
        return HttpStatus.OK;
    }


}
