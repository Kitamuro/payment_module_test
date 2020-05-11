package school.attractor.payment_module.domain.ApacheHttp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

@AllArgsConstructor
@Builder
@Data
class ResponseDTO {
    private String RetrievalReferenceNumber;
    private String InternalReferenceNumber;
    private TransactionStatus status;
    private String responseHtml;


}
