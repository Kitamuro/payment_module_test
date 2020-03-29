package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@AllArgsConstructor
@RestController
public class ControllerRest {

    @PostMapping("/pay")
    public HttpStatus mainController(@Valid @RequestBody  TransactionDTO transactionDTO) throws IOException {
        System.out.println(transactionDTO);
//        ApacheHttpClientPost.sendRequest (transactionDTO.getCARD (), transactionDTO.getEXP (), transactionDTO.getEXP_YEAR (), transactionDTO.getCVC2 ());
        return HttpStatus.OK;
    }

}
