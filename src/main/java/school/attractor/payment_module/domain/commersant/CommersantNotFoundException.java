package school.attractor.payment_module.domain.commersant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class CommersantNotFoundException extends RuntimeException {

    public CommersantNotFoundException(String message){
        super(message);
    }
}
