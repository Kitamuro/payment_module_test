package school.attractor.payment_module.domain.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.ApacheHttp.Request;
import school.attractor.payment_module.domain.ApacheHttp.Response;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.order.OrderDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    private Response response;

    @OneToOne(fetch = FetchType.LAZY)
    private Request request;

    @Column(length = 30)
    private String currency;

    @Column(length = 100)
    private int amount;

    @Column
    private double fee;

    @Column
    private Date date;

    @Column
    private TransactionStatus status;

    @Column
    private TransactionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Order order;


    public  static Transaction from(TransactionDTO transactionDTO) {
        return  Transaction.builder()
                .amount(transactionDTO.getAmount())
                .status(transactionDTO.getStatus())
                .type(transactionDTO.getType())
                .date(transactionDTO.getDate())
                .order(Order.from(transactionDTO.getOrder()))
                .build();
    }
}
