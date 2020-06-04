package school.attractor.payment_module.domain.commersant;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.FORBIDDEN)
public class CommersantAlreadyRegisteredException extends RuntimeException {

}
