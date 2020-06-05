package school.attractor.payment_module.domain.commersant;

import lombok.*;
import school.attractor.payment_module.domain.shop.Shop;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommersantDTO {

    private String id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commersant")
    private List<Shop> shops = new ArrayList<>();

    @NotBlank
    @Size(max = 30)
    private String bik;

    @NotBlank
    @Size(max = 30)
    private String bin;

    @NotBlank
    @Size(max = 30)
    private String directorIdentityCard;

    @NotBlank
    @Size(max = 30)
    private String directorName;

    @NotBlank
    @Size(max = 30)
    private String kbe;

    @NotBlank
    @Size(max = 30)
    private String organizationName;

    public static CommersantDTO from(Commersant commersant) {
        return builder()
                .name(commersant.getName())
                .organizationName(commersant.getOrganizationName())
                .bik(commersant.getBik())
                .bin(commersant.getBin())
                .directorIdentityCard(commersant.getDirectorIdentityCard())
                .directorName(commersant.getDirectorName())
                .kbe(commersant.getKbe())
                .build();
    }

    public static CommersantDTO from(CommersantRegistrationDataDTO data) {
        return builder()
                .organizationName(data.getOrganizationName())
                .bik(data.getBik())
                .bin(data.getBin())
                .directorIdentityCard(data.getDirectorIdentityCard())
                .directorName(data.getDirectorName())
                .build();
    }
}
