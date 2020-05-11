package school.attractor.payment_module.domain.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.item.ItemDTO;
import school.attractor.payment_module.domain.order.OrderDTO;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


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
