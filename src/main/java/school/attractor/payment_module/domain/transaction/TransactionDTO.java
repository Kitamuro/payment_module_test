package school.attractor.payment_module.domain.transaction;

import lombok.*;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.item.ItemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String id;
    private double amount;
    private CommersantDTO commersantDTO;
    private String cardHolderName;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCvc;
    private List<ItemDTO> items;

    public static TransactionDTO from(Transaction transaction) {
        return builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .cardHolderName(transaction.getCardHolderName())
                .cardNumber(transaction.getCardNumber())
                .cardExpiryDate(transaction.getCardExpiryDate())
                .cardCvc(transaction.getCardCvc())
                .items(transaction.getItems().stream().map(ItemDTO::from).collect(Collectors.toList()))
                .build();
    }
}
