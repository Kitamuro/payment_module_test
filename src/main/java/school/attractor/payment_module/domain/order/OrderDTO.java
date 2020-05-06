package school.attractor.payment_module.domain.order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
    String amount;
    int sum;
    List<TransactionDTO> transactions;
}