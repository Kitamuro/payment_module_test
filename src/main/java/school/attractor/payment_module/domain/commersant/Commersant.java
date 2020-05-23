package school.attractor.payment_module.domain.commersant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import school.attractor.payment_module.domain.shop.Shop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "commersants")
public class Commersant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commersant")
    private List<Shop> shops = new ArrayList<> ();




}


