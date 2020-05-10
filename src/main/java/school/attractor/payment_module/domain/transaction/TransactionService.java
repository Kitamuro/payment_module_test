package school.attractor.payment_module.domain.transaction;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;



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

    public void change(Transaction transaction) { transactionRepository.save ( transaction); }
//        public Page<Transaction> searchTransactions(TransactionSearchDTO search) {
//        Page<Transaction> pageTransactions;
////      List<Transaction>transactions = transactionRepository.findAll(Objects.requireNonNull(where(hasAmount(search.getAmount())).or(hasOrderId(search.getId())).or(shopNameContains(search.getShopName()))).or(hasStatus(search.getStatus())));
////      List<Transaction> transactions = transactionRepository.findTransactionsByOrderIdOrShopNameOrStatusOrAmount(search.getId(), search.getShopName(), search.getStatus(), search.getAmount());
//        pageTransactions =  new PageImpl<>(transactions);
//        return pageTransactions;
//    }

    public int  getSum(String orderID) {
        return transactionRepository.getSum(orderID);
    }

    public List<Transaction> getByOrderId(String orderId) {
       return transactionRepository.findAllByOrderId(orderId);
    }
}
