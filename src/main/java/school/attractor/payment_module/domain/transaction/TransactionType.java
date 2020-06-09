package school.attractor.payment_module.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {
    REFUND(0, "14"),
    PAYMENT(1, "1"),
    HOLD (2, "0"),
    AUTH(3, "21");

    private final int code;

    private final  String numberType;


}
