package school.attractor.payment_module.domain.commersant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CommersantRepository extends JpaRepository<Commersant, Integer> {
    @Override
    Optional<Commersant> findById(Integer integer);


}
