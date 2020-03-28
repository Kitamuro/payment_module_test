package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import javax.validation.Valid;
import java.io.IOException;

import static java.util.stream.Collectors.toList;


@AllArgsConstructor
@RestController
public class ControllerRest {

    @PostMapping("/pay")
    public HttpStatus mainController(@RequestBody @Valid TransactionDTO transactionDTO) throws IOException {
        System.out.println(transactionDTO);
        ApacheHttpClientPost.sendRequest (transactionDTO.getCARD (), transactionDTO.getEXP (), transactionDTO.getEXP_YEAR (), transactionDTO.getCVC2 ());
        return HttpStatus.OK;
    }

}
