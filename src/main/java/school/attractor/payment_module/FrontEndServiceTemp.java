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
        String orderNumber = "456321"; //пока не передается номер заказа
        apacheHttpClientPostTemp.sendRequest (transactionDTO.getCARD (), transactionDTO.getEXP (), transactionDTO.getEXP_YEAR (), transactionDTO.getCVC2 (), String.valueOf ( (int)transactionDTO.getAmount () ), orderNumber);
    }
}
