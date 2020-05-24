package school.attractor.payment_module.domain.commersant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommersantRegistrationDataDTO {

    @NotBlank(message = "поле БИК не должно быть пустым")
    @Size(max=30)
    private String bik;

    @NotBlank(message = "поле БИН не должно быть пустым")
    @Size(max=30)
    private String bin;

    @NotBlank(message = "Номер удостоверения личности не должно быть пустым")
    @Size(max=30)
    private String directorIdentityCard;

    @NotBlank(message = "Имя владельца не должно быть пустым")
    @Size(max=30)
    private String directorName;

    @NotBlank(message = "поле Кбе не должно быть пустым")
    @Size(max=30)
    private String kbe;

    @NotBlank(message = "Название организации не должно быть пустым")
    @Size(max=30)
    private String organizationName;

}
