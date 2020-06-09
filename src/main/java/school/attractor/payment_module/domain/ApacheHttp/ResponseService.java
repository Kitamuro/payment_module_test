package school.attractor.payment_module.domain.ApacheHttp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionStatus;
import school.attractor.payment_module.domain.transaction.TransactionRepository;
import school.attractor.payment_module.domain.transaction.TransactionType;

@Service
@AllArgsConstructor
public class ResponseService {


    private ResponseRepository responseRepository;
    private TransactionRepository transactionRepository;
    private OrderService orderService;
    private RequestRepository requestRepository;



    private void save(Transaction transaction, ResponseDTO responseDTO, Request request) {
        Response response = Response.from(responseDTO, transaction);
        requestRepository.save ( request );
        responseRepository.save ( response );
        transaction.setResponse ( response );
        transaction.setRequest ( request );
        transactionRepository.save ( transaction );
    }




    public String sendRequest(Transaction transaction) {

        SendRequest sendRequest = new SendRequest ( transaction, transaction.getType().getNumberType() );
        save ( transaction, sendRequest.getResponseDTO ( ), sendRequest.getRequest () );
        if (sendRequest.getResponseDTO ( ).getStatus ().equals ( TransactionStatus.APPROVED )) {
            transaction.setStatus ( TransactionStatus.APPROVED );
            transactionRepository.save ( transaction );
            orderService.setOrderParam (transaction.getOrder (), transaction, sendRequest.getResponseDTO ( ).getInternalReferenceNumber ( ),sendRequest.getResponseDTO ( ).getRetrievalReferenceNumber ( ) );
            orderService.change(transaction.getOrder ());
            return "SUCCESS";
        }else{
            transaction.setStatus ( TransactionStatus.REFUSED );
            transactionRepository.save ( transaction );
            if(transaction.getOrder ().getStatus ().equals ( TransactionStatus.NEW )){
                transaction.getOrder ().setStatus ( TransactionStatus.REFUSED );
            }
            return "REFUSED";
        }
    }


}
