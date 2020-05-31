package school.attractor.payment_module.domain.shop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(String message){
        super(message);
    }
}
