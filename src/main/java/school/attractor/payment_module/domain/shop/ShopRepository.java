package school.attractor.payment_module.domain.shop;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.attractor.payment_module.domain.commersant.Commersant;

import java.util.List;

@Repository
public interface ShopRepository  extends JpaRepository<Shop, Integer> {

    List<Shop> findAllByCommersant(Commersant commersant);


}
