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

    public Page<Transaction> searchTransactions(TransactionSearchDTO search, Pageable pageable){
        List<Transaction> transactions;
        if (search.getId() == null && search.getStatus() == null && search.getShopName() != null){
            transactions = transactionRepository.getAllByShopName(search.getShopName(), pageable);
        }else if(search.getId() == null && search.getStatus() != null && search.getShopName() == null){
            transactions = transactionRepository.getAllByStatus(search.getStatus(), pageable);
        }else if(search.getId() != null && search.getStatus() == null && search.getShopName() == null){
            transactions = transactionRepository.getAllById(search.getId(), pageable);
        }else if(search.getId() == null && search.getStatus() != null && search.getShopName() != null){
            transactions = transactionRepository.getAllByStatusAndShopName(search.getStatus(), search.getShopName(), pageable);
        }else if(search.getId() != null && search.getStatus() == null && search.getShopName() != null){
            transactions = transactionRepository.getAllByIdAndShopName(search.getId(), search.getShopName(), pageable);
        }
        else if(search.getId() != null && search.getStatus() != null && search.getShopName() == null){
            transactions = transactionRepository.getAllByIdAndStatus(search.getId(), search.getStatus(), pageable);
        }else{
            transactions = transactionRepository.getAllByIdAndStatusAndShopName(search.getId(), search.getStatus(), search.getShopName(), pageable);
        }

        int pageSize = pageable.getPageSize ();
        int currentPage = pageable.getPageNumber ();
        int initialItem = currentPage * pageSize;
        List<Transaction> transactionList;

        if (transactions.size () < initialItem){
            transactionList= Collections.emptyList ();
        }else{
            int lastIndexOfItem = Math.min(initialItem + pageSize, transactions.size ());
            transactionList = transactions.subList ( initialItem, lastIndexOfItem );
        }
        return new PageImpl<Transaction>(transactionList, PageRequest.of(currentPage, pageSize ), transactions.size ());
    }
}
