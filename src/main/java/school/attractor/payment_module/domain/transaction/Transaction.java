package school.attractor.payment_module.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Commersant commersant;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Response> responses = new ArrayList<> ();

    @Column(length = 1)
    private String hold = "0";

    @Column(length = 50)
    private String shopId;

    @Column(length = 30)
    private String shopName;

    @Column(length = 30)
    private String userName;

    @Column(length = 30)
    @Email
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(length = 50)
    private String cardHolderName;

    @Column(length = 30)
    private String CARD;

    @Column(length = 10)
    private String EXP;

    @Column(length = 10)
    private String EXP_YEAR;

    @Column(length = 10)
    private String CVC2;

    @Column(length = 30)
    private String currency;

    @Column(length = 100)
    private Integer amount;

    @Column
    private double fee;

    @Column
    private Date date;

    @Column
    private TransactionStatus status;

    @Column
    private TransactionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;


    public  static Transaction from(TransactionDTO transactionDTO) {
        return  Transaction.builder()
                .order(OrderDTO.from(transactionDTO.getOrder ()))
                .shopId(transactionDTO.getShopId())
                .shopName(transactionDTO.getShopName())
                .userName(transactionDTO.getUserName())
                .amount(transactionDTO.getAmount())
                .email(transactionDTO.getEmail())
                .status(transactionDTO.getStatus())
                .type(transactionDTO.getType())
                .EXP(transactionDTO.getEXP())
                .EXP_YEAR(transactionDTO.getEXP_YEAR())
                .cardHolderName(transactionDTO.getCardHolderName())
                .date(transactionDTO.getDate())
                .phone(transactionDTO.getPhone())
                .build();
    }
}
