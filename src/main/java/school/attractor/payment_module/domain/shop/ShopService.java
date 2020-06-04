package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.commersant.CommersantNotFoundException;
import school.attractor.payment_module.domain.commersant.CommersantRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopService {

    private ShopRepository shopRepository;
    public CommersantRepository commersantRepository;


    private Commersant findCommersant(Principal principal){
        return commersantRepository.findByEmail ( principal.getName ( ) ).orElseThrow (()->new CommersantNotFoundException ( "No commersant is found" ) );
    }

    public void createShop(ShopDTO shopDTO, Principal principal) {
        Shop shop = Shop.from ( shopDTO );
        shop.setCommersant (findCommersant (  principal));
        shopRepository.save ( shop );
    }

    public List<Shop> getShops(Principal principal) {
        Commersant commersant = findCommersant ( principal );
        return shopRepository.findAllByCommersant (commersant );
    }

    public Shop getShop(Integer shopId) {
        return shopRepository.findById ( shopId ).orElseThrow ( ShopNotFoundException::new );
    }

    public void changePaymentType(int shopId, int hold) {
        Shop shop = shopRepository.findById ( shopId ).orElseThrow ( ShopNotFoundException::new );
        shop.setHold ( hold );
        shopRepository.save ( shop );
    }
}
