package school.attractor.payment_module.domain.transaction;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;


    public void save(TransactionDTO transactionDTO) {
        Transaction transaction = Transaction.from(transactionDTO);
        transactionRepository.save(transaction);
    }

    public Page<Transaction> getTransactions(Pageable pageable){
        List<Transaction> transactions = transactionRepository.findAll ( );
        int pageSize = pageable.getPageSize ();
        int currentPage = pageable.getPageNumber ();
        int initialItem = currentPage * pageSize;
        List<Transaction> transactionList;

        if (transactions.size () < initialItem){
            transactionList= Collections.emptyList ();
        }else{
            int lastIndexofItem = Math.min(initialItem + pageSize, transactions.size ());
            transactionList = transactions.subList ( initialItem, lastIndexofItem );
        }
        return new PageImpl<Transaction> (transactionList, PageRequest.of(currentPage, pageSize ), transactions.size ());
//    https://www.baeldung.com/spring-thymeleaf-pagination
    }

}
