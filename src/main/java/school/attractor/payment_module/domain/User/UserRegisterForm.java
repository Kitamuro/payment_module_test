package school.attractor.payment_module.domain.User;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegisterForm {
    @NotBlank
    @Email
    private String email="";

    @NotBlank
    @Size(min = 6, max = 24, message = "Length must be >= 6 and <= 24")
    private String password = "";
}
