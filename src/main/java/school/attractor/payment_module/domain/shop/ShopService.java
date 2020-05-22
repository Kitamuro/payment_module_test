package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopService {

    private ShopRepository shopRepository;


    public void createShop(Shop shop) {
        shopRepository.save ( shop );
    }
}
