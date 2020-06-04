package school.attractor.payment_module.domain.shop;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class ShopController implements WebMvcConfigurer {

    private ShopService shopService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/shops").setViewName("shops");
    }

    @GetMapping("/shops")
    public String getShops (@ModelAttribute("shopDTO") ShopDTO shopDTO, Model model, Principal principal){
        List<Shop> shops = shopService.getShops (principal );
        if(shops.size ()!=0){
            model.addAttribute ( "shops", shops  );
        }
        return "shops";
    }

    @PostMapping("/shops")
    public String createShop(@ModelAttribute @Valid ShopDTO shopDTO, BindingResult result, Model model){
        System.out.println("QWEQWEWWQEQEQWEQE" + shopDTO);
        if (result.hasErrors()) {
                model.addAttribute("errors", result.getAllErrors());
            return "shops";
        } else {
            model.addAttribute("shopDTO", shopDTO);
            shopService.createShop ( shopDTO );
            return "redirect:/shops";
        }
    }

    @GetMapping("shops/about/{shopId}")
    public String getShop (Model model, @PathVariable Integer shopId){
        Shop shop = shopService.getShop ( shopId );
        model.addAttribute ( "shop", shop);
        return "about-shop.html";
    }

    @GetMapping("/paymentType/{shopId}")
    public String getShopPaymentType (Model model, @PathVariable("shopId") Integer shopId){
        Shop shop = shopService.getShop ( shopId );
        model.addAttribute ( "shop", shop);
        return "payment-type";
    }

    @PostMapping("/paymentType")
    public String changePaymentType (@RequestParam int shopId, @RequestParam int hold ){
        shopService.changePaymentType(shopId, hold);
        System.out.println (shopId + hold );
        return "shops";
    }
}
