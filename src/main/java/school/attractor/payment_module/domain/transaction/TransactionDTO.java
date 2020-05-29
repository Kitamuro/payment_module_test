package school.attractor.payment_module.domain.transaction;


import lombok.*;
import java.util.Date;



@AllArgsConstructor
@Builder
@Data
public class TransactionDTO {
    private int id;
    private int amount;
    private String currency;
    private TransactionType type;
    private TransactionStatus status;
    private Date date;

    public static TransactionDTO from(Transaction transaction) {
        return builder()
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .type(transaction.getType())
                .date(transaction.getDate()).build();

    }


}
