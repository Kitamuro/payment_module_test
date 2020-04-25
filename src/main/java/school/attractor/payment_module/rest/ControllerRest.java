package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.attractor.payment_module.domain.ApacheHttp.Response;
import school.attractor.payment_module.domain.ApacheHttp.SendRequest;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@CrossOrigin
@AllArgsConstructor
@RestController
public class ControllerRest {

    TransactionService transactionService;

    @PostMapping("/pay")
    public ResponseEntity mainController(@Valid @RequestBody TransactionDTO transactionDTO, HttpServletRequest request) throws IOException {
        System.out.println ( transactionDTO );
        Transaction transaction = transactionService.save ( transactionDTO );
        SendRequest sendRequest = new SendRequest ( transaction, transaction.getAmount (),transaction.getHold () );
        String responseCode = sendRequest.getResponse ( ).getRcCode ( );

//        return ResponseEntity.status(HttpStatus.OK).body("okay");

        if (responseCode.equals("00")){
            List<Response> responses = transaction.getResponses ( );
            responses.add (sendRequest.getResponse ());
            transaction.setStatus ( "Деньги заблокированы" );
            return ResponseEntity.status(HttpStatus.OK).body("okay");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseCode);
        }

//            return  ResponseEntity.status(HttpStatus.CONFLICT).body("not okay?");
    }
}
