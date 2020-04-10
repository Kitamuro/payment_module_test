package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@AllArgsConstructor
@NoArgsConstructor
@RestController
public class ControllerRest {
    private ApacheHttpClientPost apacheHttpClientPostTemp = new ApacheHttpClientPost();

    @PostMapping("/pay")
    public ResponseEntity mainController(@Valid @RequestBody  TransactionDTO transactionDTO, HttpServletRequest request) throws IOException {
        System.out.println(transactionDTO);
        String orderId = "342121";
        apacheHttpClientPostTemp.sendRequest(transactionDTO.getCARD(), transactionDTO.getEXP(), transactionDTO.getEXP_YEAR(), transactionDTO.getCVC2(), transactionDTO.getAmount(), orderId);

        String responseCode = apacheHttpClientPostTemp.getResponseCode();
        String errorMessage = apacheHttpClientPostTemp.getErrorMessage();
        if (responseCode.equals("00")){
            return ResponseEntity.status(HttpStatus.OK).body("okay");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
        }

//            return  ResponseEntity.status(HttpStatus.CONFLICT).body("not okay?");
    }

}
