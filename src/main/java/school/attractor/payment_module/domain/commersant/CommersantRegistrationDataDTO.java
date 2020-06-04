package school.attractor.payment_module.domain.commersant;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommersantRegistrationDataDTO {

    @NotBlank
    @Email
    private String email="";

    @NotBlank
    @Size(min = 6, max = 24, message = "Размер пароль должен быть не меньше 6 и не больше 24 символов")
    private String password = "";

    @NotBlank(message = "поле БИК не должно быть пустым")
    @Size(max = 8, min = 8, message = "Вы ввели неверный БИК")
    @Pattern(regexp = "[A-Z ]+", message = "Неверный формат БИК, только буквы в верхнем регистре")
    private String bik;

    @NotBlank(message = "поле БИН не должно быть пустым")
    @Size(max = 12, min = 12, message = "Вы ввели неверный БИН")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Неверный формат БИН, только цифры (12-ти значный код)")
    private String bin;

    @NotBlank(message = "Номер удостоверения личности не должно быть пустым")
    @Size(max = 9, min = 9, message = "Вы ввели неверный номер удостоверения личности")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Неверный формат номера удостверения личности, только цифры (9-значный код)")
    private String directorIdentityCard;

    @NotBlank(message = "Имя владельца не должно быть пустым")
    @Size(max=30)
    private String directorName;

    @NotBlank(message = "Название организации не должно быть пустым")
    @Size(max=30)
    private String organizationName;
}
