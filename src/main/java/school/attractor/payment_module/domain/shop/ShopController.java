package school.attractor.payment_module.domain.shop;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;


    @GetMapping("/shops")
    public String getShops (Model model){
        return "shops";
    }



}
