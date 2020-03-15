package school.attractor.payment_module.domain.ApacheHttp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    String cardNumber;
    String cardExpiry;
    Double transactionAmount;
    String transactionCcy;
    String merchantId;
    String transactionReference;
    String internalTransReference;
    String approvalCode;
    String rcCode;
}
