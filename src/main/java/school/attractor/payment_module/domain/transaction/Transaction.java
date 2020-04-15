package school.attractor.payment_module.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.Commersant;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;



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
    private String amount;

    @Column
    private double fee;

    @Column
    private Date date;

    @Column(length = 30)
    private String status;

    @Column(length = 30)
    private String orderId;
}
