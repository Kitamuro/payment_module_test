package school.attractor.payment_module;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import school.attractor.payment_module.domain.ApacheHttp.ApacheHttpClientPost3D;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FrontEndService3D {

    public ApacheHttpClientPost3D apacheHttpClientPost3D = new ApacheHttpClientPost3D (  );


    void makeHttpClient(TransactionDTO transactionDTO) throws IOException {
        apacheHttpClientPost3D.sendRequest (transactionDTO.getCARD (), transactionDTO.getEXP (), transactionDTO.getEXP_YEAR (), transactionDTO.getCVC2 ());
    }
}
