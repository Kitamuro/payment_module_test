package school.attractor.payment_module.domain.ApacheHttp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    LocalDateTime dateTime;
    String cardNumber;
    String cardExpiry;
    Double transactionAmount;
    String transactionCcy;
    String merchantId;
    String RetrievalReferenceNumber;
    String internalReferenceNumber;
    String approvalCode;
    String rcCode;



}
