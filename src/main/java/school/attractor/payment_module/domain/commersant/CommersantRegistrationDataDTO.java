package school.attractor.payment_module.domain.commersant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommersantRegistrationDataDTO {

    @NotBlank
    private String bik;
    @NotBlank
    private String bin;
    @NotBlank
    private String directorIdentityCard;
    @NotBlank
    private String directorName;
    @NotBlank
    private String kbe;
    @NotBlank
    private String organizationName;

}
