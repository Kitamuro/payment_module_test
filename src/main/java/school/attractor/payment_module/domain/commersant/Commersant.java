package school.attractor.payment_module.domain.commersant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import school.attractor.payment_module.domain.shop.Shop;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(max=30)
    private String bik;

    private String account;

    @NotBlank
    @Size(max=30)
    private String bin;

    @NotBlank
    @Size(max=30)
    private String directorIdentityCard;

    @NotBlank
    @Size(max=30)
    private String directorName;

    @NotBlank
    @Size(max=30)
    private String kbe;

    @NotBlank
    @Size(max=30)
    private String organizationName;

    public static Commersant from(CommersantDTO commersant) {
        return  builder()
             .name(commersant.getName())
                .bik(commersant.getBik())
                .bin(commersant.getBin())
                .directorIdentityCard(commersant.getDirectorIdentityCard())
                .directorName(commersant.getDirectorName())
                .kbe(commersant.getKbe())
                .organizationName(commersant.getOrganizationName())
                .build();
    }

}


