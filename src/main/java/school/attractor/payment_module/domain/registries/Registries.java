package school.attractor.payment_module.domain.registries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.order.Order;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "registries")
public class Registries {

    @Column
    private Date date;

    @Column
    private Commersant commersant;

    @Column
    private Order order;

    @Column
    private int kbe = 17;

    @Column
    private int knp = 852;

    @Column
    private String transitAccount = "KZ47826A0KZT0T006904";
}
