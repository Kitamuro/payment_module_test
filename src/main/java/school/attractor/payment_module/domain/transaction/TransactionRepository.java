package school.attractor.payment_module.domain.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findAllByOrderId(String id, Pageable pageable);
    Page<Transaction> findAllByOrderIdAndStatus(String id, String status, Pageable pageable);
    Page<Transaction> findAllByOrderIdAndShopName(String id, String shopName, Pageable pageable);
    Page<Transaction> findAllByStatus(String status, Pageable pageable);
    Page<Transaction> findAllByShopName(String shopName, Pageable pageable);
    Page<Transaction> findAllByStatusAndShopName (String status, String shopName, Pageable pageable);
    Page<Transaction> findAllByOrderIdAndStatusAndShopName (String id, String status, String shopName, Pageable pageable);
}
