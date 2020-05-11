package school.attractor.payment_module.domain.ApacheHttp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderService;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionRepository;
import school.attractor.payment_module.domain.transaction.TransactionStatus;
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


    private String getTrType(Transaction transaction) {
        TransactionType type = transaction.getType ( );
        switch (type) {
            case REFUND:
                return "14";
            case PAYMENT:
                return "1";
            case HOLD:
                return "0";
            default:
                return "21";
        }

    }

    public String sendRequest(Transaction transaction) {
        String trType = getTrType ( transaction );
        SendRequest sendRequest = new SendRequest ( transaction, trType );
        save ( transaction, sendRequest.getResponseDTO ( ), sendRequest.getRequest () );
        if (sendRequest.getResponseDTO ( ).getStatus ().equals ( TransactionStatus.APPROVED )) {
            transaction.setStatus ( TransactionStatus.APPROVED );
            transactionRepository.save ( transaction );
            orderService.setOrderStatus(transaction.getOrder (), transaction);
            transaction.getOrder ().setInternalReferenceNumber ( sendRequest.getResponseDTO ( ).getInternalReferenceNumber ( ) );
            transaction.getOrder ().setRetrievalReferenceNumber ( sendRequest.getResponseDTO ( ).getRetrievalReferenceNumber ( ) );
            orderService.change(transaction.getOrder ());
            return "SUCCESS";
        }else{
            transaction.setStatus ( TransactionStatus.REFUSED );
            transactionRepository.save ( transaction );
            return "REFUSED";
        }
    }


}
