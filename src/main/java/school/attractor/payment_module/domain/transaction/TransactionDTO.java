package school.attractor.payment_module.domain.transaction;

import lombok.*;
import school.attractor.payment_module.domain.commersant.CommersantDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String id;
    private double orderAmount;
    private CommersantDTO commersantDTO;
    private String cardHolderName;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCvc;

    static TransactionDTO from(Transaction transaction) {
        return builder()
                .id(transaction.getId())
                .orderAmount(transaction.getOrderAmount())
                .cardHolderName(transaction.getCardHolderName())
                .cardNumber(transaction.getCardNumber())
                .cardExpiryDate(transaction.getCardExpiryDate())
                .cardCvc(transaction.getCardCvc())
                .build();
    }
}
