package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.shop.Shop;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Email
    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String email;

    @NotBlank
    @Size(min = 6, max = 128)
    @Column(length = 128)
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commersant")
    private List<Shop> shops = new ArrayList<> ();

    @Size(max=30)
    private String bik;


    private String account;


    @Size(max=30)
    private String bin;


    @Size(max=30)
    private String directorIdentityCard;


    @Size(max=30)
    private String directorName;


    @Size(max=30)
    @Builder.Default
    private String kbe = "19";


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


