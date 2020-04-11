package school.attractor.payment_module.domain.transaction;

import lombok.*;
import school.attractor.payment_module.domain.ApacheHttp.ResponseDTO;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.item.ItemDTO;

import javax.validation.constraints.*;
import java.util.List;


@AllArgsConstructor
@Builder
@Data
public class TransactionDTO {
    private String id;
    private CommersantDTO commersantDTO;
    private ResponseDTO responseDTO;
    private String shopId;

    @NotBlank(message = "Введите ваше полное имя")
    private String userName;

    @NotBlank(message = "Введите корректный email адрес")
    @Email
    private String email;

    @NotBlank(message = "Введите существующий номер телефона")
//    НА САМОМ ДЕЛЕ МЕНЬШЕ ТЕЛ НОМЕР
    @Size(min = 15, max = 15, message = "Вы ввели неправильный номер")
    private String phone;

    @NotBlank(message = "Введите правильное имя на карте")
    private String cardHolderName;

    @NotBlank(message = "Введите номер существующей карты")
//    @Digits(integer = 16, fraction = 0, message = "Вы ввели неправильную карту")
    @Size(min = 16, max = 16, message = "Вы ввели неправильную карту")
    private String CARD;

    @NotBlank(message = "Введите правильный месяц истечения срока вашей карты")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильные данные месяца")
    @Size(min = 1, max = 2)
    private String EXP;

    @NotBlank(message = "Введите правильный год истечения срока вашей карты")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильный данные года")
    @Size(min = 4, max = 4)
    private String EXP_YEAR;

    @NotBlank(message = "Введите правильный код на обратной стороне карты")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильный код")
    private String CVC2;

    private List<ItemDTO> items;
    private String amount;

    public static TransactionDTO from(Transaction transaction) {
        return builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                 .shopId(transaction.getShopId())
                .userName(transaction.getUserName())
                .email(transaction.getEmail())
                .phone(transaction.getPhone())
                .cardHolderName(transaction.getCardHolderName())
                .CARD(transaction.getCARD())
                .EXP(transaction.getEXP())
                .EXP_YEAR(transaction.getEXP_YEAR())
                .CVC2(transaction.getCVC2())
//                .items(transaction.getItems().stream().map(ItemDTO::from).collect(Collectors.toList()))
                .build();
    }
}
