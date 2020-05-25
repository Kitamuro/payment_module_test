package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {

    private ShopRepository shopRepository;


    public void createShop(ShopDTO shopDTO) {
        Shop shop = Shop.from ( shopDTO );
        shopRepository.save ( shop );
    }

    public List<Shop> getShops() {
        List<Shop> shops = shopRepository.findAll ( );
        return shops;
    }
}
