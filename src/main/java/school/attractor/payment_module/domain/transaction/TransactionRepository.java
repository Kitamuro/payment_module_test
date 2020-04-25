package school.attractor.payment_module.domain.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getAllById(Integer id, Pageable pageable);
    List<Transaction> getAllByIdAndStatus(Integer id, String status, Pageable pageable);
    List<Transaction> getAllByIdAndShopName (Integer id, String shopName, Pageable pageable);
    List<Transaction> getAllByStatus(String status, Pageable pageable);
    List<Transaction> getAllByShopName(String shopName, Pageable pageable);
    List<Transaction> getAllByStatusAndShopName (String status, String shopName, Pageable pageable);
    List<Transaction> getAllByIdAndStatusAndShopName (Integer id, String status, String shopName, Pageable pageable);
}
