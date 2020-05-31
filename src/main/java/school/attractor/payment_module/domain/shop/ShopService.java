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


    public void createShop(ShopDTO shopDTO) {
        Shop shop = Shop.from ( shopDTO );
        shopRepository.save ( shop );
    }

    public List<Shop> getShops(Principal principal) {
        var commersant = commersantRepository.findByEmail ( principal.getName ( ) ).orElseThrow (()->new CommersantNotFoundException ( "" ) );
        return shopRepository.findAllByCommersant (commersant );
    }

    public Shop getShop(Integer shopId) {
        return shopRepository.findById ( shopId ).orElseThrow ( () -> new ShopNotFoundException() );
    }
}
