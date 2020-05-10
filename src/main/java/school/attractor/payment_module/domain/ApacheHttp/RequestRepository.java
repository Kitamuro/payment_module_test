package school.attractor.payment_module.domain.ApacheHttp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
