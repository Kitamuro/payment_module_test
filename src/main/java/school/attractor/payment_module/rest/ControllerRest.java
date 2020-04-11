package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Random;


@AllArgsConstructor
@RestController
public class ControllerRest {


    @PostMapping("/pay")
    public ResponseEntity mainController(@Valid @RequestBody TransactionDTO transactionDTO, HttpServletRequest request) throws IOException {
        System.out.println(transactionDTO);
        Random random = new Random();
        String orderId = String.valueOf ( random.nextInt ( 999999 ) + 100000 );
        ApacheHttpClientPost apacheHttpClientPost = new ApacheHttpClientPost ( transactionDTO.getCARD ( ), transactionDTO.getEXP ( ), transactionDTO.getEXP_YEAR ( ), transactionDTO.getCVC2 ( ), transactionDTO.getAmount ( ), orderId );
        String responseCode = apacheHttpClientPost.getResponseDTO ().getRcCode ();
        if (responseCode.equals("00")){
            transactionDTO.setResponseDTO ( apacheHttpClientPost.getResponseDTO ());
            return ResponseEntity.status(HttpStatus.OK).body("okay");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseCode);
        }

//            return  ResponseEntity.status(HttpStatus.CONFLICT).body("not okay?");
    }

}
