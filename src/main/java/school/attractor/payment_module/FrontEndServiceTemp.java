package school.attractor.payment_module;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FrontEndServiceTemp {

    public ApacheHttpClientPost apacheHttpClientPostTemp = new ApacheHttpClientPost (  );


    void makeHttpClient(TransactionDTO transactionDTO) throws IOException {
        apacheHttpClientPostTemp.sendRequest (transactionDTO.getCARD (), transactionDTO.getEXP (), transactionDTO.getEXP_YEAR (), transactionDTO.getCVC2 ());
    }
}
