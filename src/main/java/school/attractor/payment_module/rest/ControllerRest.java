package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionSearchDTO;
import school.attractor.payment_module.domain.transaction.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CrossOrigin
@AllArgsConstructor
@RestController
public class ControllerRest {

    TransactionService transactionService;

    @PostMapping("/pay")
    public ResponseEntity mainController(@Valid @RequestBody TransactionDTO transactionDTO, HttpServletRequest request) throws IOException {
        System.out.println(transactionDTO);
//        Random random = new Random();
//        String orderId = String.valueOf ( random.nextInt ( 999999 ) + 100000 );
//        ApacheHttpClientPost apacheHttpClientPost = new ApacheHttpClientPost ( transactionDTO.getCARD ( ), transactionDTO.getEXP ( ), transactionDTO.getEXP_YEAR ( ), transactionDTO.getCVC2 ( ), transactionDTO.getAmount ( ), orderId );
//        String responseCode = apacheHttpClientPost.getResponseDTO ().getRcCode ();

        transactionService.save(transactionDTO);

        return ResponseEntity.status(HttpStatus.OK).body("okay");

//
//        if (responseCode.equals("00")){
//            transactionDTO.setResponseDTO ( apacheHttpClientPost.getResponseDTO ());
//            return ResponseEntity.status(HttpStatus.OK).body("okay");
//        }else{
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseCode);
//        }

//            return  ResponseEntity.status(HttpStatus.CONFLICT).body("not okay?");
    }


}
