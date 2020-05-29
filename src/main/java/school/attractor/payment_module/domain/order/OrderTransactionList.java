package school.attractor.payment_module.domain.order;

import lombok.*;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionStatus;
import school.attractor.payment_module.domain.transaction.TransactionType;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTransactionList {
    private int id;
    private int amount;
    private String currency;
    private TransactionType type;
    private TransactionStatus status;
    private Date date;

    public static OrderTransactionList from(Transaction transaction) {
        return builder()
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .type(transaction.getType())
                .date(transaction.getDate()).build();

    }

    public static OrderTransactionList from(TransactionDTO transaction) {
        return builder()
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .type(transaction.getType())
                .date(transaction.getDate()).build();

    }
}
