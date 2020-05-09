package school.attractor.payment_module.domain.order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
    String shopName;
    String userName;
    String email;
    Date date;
    int amount;
    TransactionStatus status;
    List<TransactionDTO> transactions;

    public static Order from(OrderDTO orderDTO) {
        return  Order.builder()
                .shopName(orderDTO.getShopName())
                .userName(orderDTO.getUserName())
                .amount(orderDTO.getAmount())
                .email(orderDTO.getEmail())
                .status(orderDTO.getStatus())
                .date(orderDTO.getDate())
                .build();
    }
}