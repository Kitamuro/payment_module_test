package school.attractor.payment_module.domain.transaction;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Page<Transaction> searchTransactions(TransactionSearchDTO search, Pageable pageable) {
        Page<Transaction> transactions;

        if (search.getId().isEmpty() && search.getStatus().isEmpty() && !search.getShopName().isEmpty()) {
            transactions = transactionRepository.findAllByShopNameContaining(search.getShopName(), pageable);
        } else if (search.getId().isEmpty() && !search.getStatus().isEmpty() && search.getShopName().isEmpty()) {
            transactions = transactionRepository.findAllByStatusContaining(search.getStatus(), pageable);
        } else if (!search.getId().isEmpty() && search.getStatus().isEmpty() && search.getShopName().isEmpty()) {
            transactions = transactionRepository.findAllByOrderId(search.getId(), pageable);
        } else if (search.getId().isEmpty() && !search.getStatus().isEmpty() && !search.getShopName().isEmpty()) {
            transactions = transactionRepository.findAllByStatusAndShopNameContaining(search.getStatus(), search.getShopName(), pageable);
        } else if (!search.getId().isEmpty() && search.getStatus().isEmpty() && !search.getShopName().isEmpty()) {
            transactions = transactionRepository.findAllByOrderIdAndShopNameContaining(search.getId(), search.getShopName(), pageable);
        } else if (!search.getId().isEmpty() && !search.getStatus().isEmpty() && search.getShopName().isEmpty()) {
            transactions = transactionRepository.findAllByOrderIdAndStatusContaining(search.getId(), search.getStatus(), pageable);
        } else {
            transactions = transactionRepository.findAllByOrderIdAndStatusAndShopNameContaining(search.getId(), search.getStatus(), search.getShopName(), pageable);
        }

        return transactions;
    }
}
