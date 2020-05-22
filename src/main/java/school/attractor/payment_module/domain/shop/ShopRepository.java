package school.attractor.payment_module.domain.shop;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository  extends JpaRepository<Shop, Integer> {
}
