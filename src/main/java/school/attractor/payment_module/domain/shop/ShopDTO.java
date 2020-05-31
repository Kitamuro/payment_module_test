package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.BusinessActivity;
import school.attractor.payment_module.domain.commersant.Commersant;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {

    private int id;
    private Commersant commersant;
    private BusinessActivity activity;

    @NotBlank(message = "Введите имя сайта")
    private String siteName;

    @Email
    @NotBlank(message = "Введите корректно email адрес")
    private String registerEmail;

    @NotBlank(message = "Введите корректно счет аккаунта")
    private String account;

    @NotBlank(message = "Введите ваш индекс города")
    private String index;

    @NotBlank(message = "Введите ваш регион")
    private String region;

    @NotBlank(message = "Введите корректно название населенного пункта")
    private String locality;

    @NotBlank(message = "Введите существующую улицу")
    private String street;

    @NotBlank(message = "Введите номер дома/строения")
    private String building;

    @NotBlank(message = "Введите номер дома/строения")
    private String house;

    @NotBlank(message = "Введите номер офиса")
    private String office;

    @NotBlank(message = "Введите номер офиса")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильные номер телефона")
    @Size(min = 15, max = 15, message = "Вы ввели неправильное количество цифр")
    private String phoneForCustomer;

    @Email
    @NotBlank(message = "Введите корректный email адрес")
    private String emailForCustomer;

    @Email
    @NotBlank(message = "Введите корректный email адрес")
    private String contactEmail;

    @NotBlank(message = "Введите имя сайта")
    private String contactName;

    @NotBlank(message = "Введите номер офиса")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильные номер телефона")
    @Size(min = 15, max = 15, message = "Вы ввели неправильное количество цифр")
    private String contactPhone;


}
