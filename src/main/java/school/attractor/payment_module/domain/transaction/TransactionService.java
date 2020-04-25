package school.attractor.payment_module.domain.transaction;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;


    public Transaction save(TransactionDTO transactionDTO) {
        Transaction transaction = Transaction.from(transactionDTO);
        transactionRepository.save(transaction);
        return transaction;
    }


    public Page<Transaction> getTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Transaction getTransaction(Integer transactionId) {
        return transactionRepository.findById ( transactionId ).orElse ( new Transaction () );
    }

}
