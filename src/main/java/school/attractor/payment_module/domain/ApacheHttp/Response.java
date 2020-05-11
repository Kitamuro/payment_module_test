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
@Table(name = "responses")
public class Response {

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


    public  static Response from(ResponseDTO responseDTO, Transaction transaction) {
        return Response.builder ( )
                .transaction ( transaction )
                .htmlString ( responseDTO.getResponseHtml ( ) )
                .date ( new Date ( ) )
                .build ( );
    }


}
