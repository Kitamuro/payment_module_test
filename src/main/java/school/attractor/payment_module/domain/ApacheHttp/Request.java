package school.attractor.payment_module.domain.ApacheHttp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.transaction.Transaction;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    Transaction transaction;

    @Column
    Date date;

    @Column
    String htmlString;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})

    public  static Request from(String  htmlString, Transaction transaction) {
        return Request.builder ( )
                .transaction ( transaction )
                .htmlString ( htmlString )
                .date ( new Date ( ) )
                .build ( );
    }
}
