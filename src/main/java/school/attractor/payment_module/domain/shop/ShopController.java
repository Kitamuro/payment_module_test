package school.attractor.payment_module.domain.shop;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;

    @GetMapping("/shops")
    public String getShops (Model model){
        List<Shop> shops = shopService.getShops ( );
        if(shops.size ()!=0){
            model.addAttribute ( "shop", shops.get ( 0)  );
        }
        return "shops/shops.html";
    }

    @PostMapping("/shops")
    public String createShop(ShopDTO shopDTO){
        System.out.println ("shop" + shopDTO );
        shopService.createShop ( shopDTO );
        return "shops/shops.html";
    }

    @GetMapping("/aboutShop")
    public String getShop (Model model){
        List<Shop> shops = shopService.getShops ( );
        if(shops.size ()!=0){
            model.addAttribute ( "shop", shops.get ( 0)  );
        }
        return "shops/aboutShop.html";
    }

    @GetMapping("/paymentType")
    public String getShopPaymentType (Model model){
        List<Shop> shops = shopService.getShops ( );
        if(shops.size ()!=0){
            model.addAttribute ( "shop", shops.get ( 0)  );
        }
        return "shops/paymentType";
    }

    @PostMapping("/paymentType")
    public String changePaymentType (@RequestParam int shopId, @RequestParam int hold ){
        System.out.println (shopId + hold );
        return "shops/paymentType";
    }
}
