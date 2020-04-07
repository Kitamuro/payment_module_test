package school.attractor.payment_module.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin
@AllArgsConstructor
@RestController
public class ControllerRest {

    @PostMapping("/pay")
    public String mainController(@Valid @RequestBody  TransactionDTO transactionDTO, HttpServletRequest request) throws IOException {
        System.out.println(transactionDTO);
        return ResponseEntity.ok().toString();

    }

}
