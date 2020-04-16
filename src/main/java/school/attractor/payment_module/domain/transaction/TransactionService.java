package school.attractor.payment_module.domain.transaction;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;


    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
