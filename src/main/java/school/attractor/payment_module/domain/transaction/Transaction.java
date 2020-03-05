package school.attractor.payment_module.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.Commersant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private String id;
    private double orderAmount;
    private Commersant commersant;
    private String cardHolderName;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCvc;
    private String currency = "KZT";
    private double fee = orderAmount/100;
}
