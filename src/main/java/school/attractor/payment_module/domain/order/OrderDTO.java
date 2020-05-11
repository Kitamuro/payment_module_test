package school.attractor.payment_module.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionStatus;
import school.attractor.payment_module.domain.transaction.TransactionType;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {
    private int id;
    private String shopName;

    @NotBlank(message = "Введите ваше полное имя")
    private String userName;

    @NotBlank(message = "Введите корректный email адрес")
    @Email
    private String email;

    @NotBlank(message = "Введите существующий номер телефона")
    @Size(min = 15, max = 15, message = "Вы ввели неправильный номер")
    private String phone;

    private Date date;

    @NotBlank(message = "Введите правильное имя на карте")
    private String cardHolderName;

//    @NotBlank(message = "Введите номер существующей карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильные данные карты")
//    @Size(min = 16, max = 16, message = "Вы ввели неправильную карту")
    private String CARD = "5447720305380381";

//    @NotBlank(message = "Введите правильный месяц истечения срока вашей карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильные данные месяца")
//    @Size(min = 1, max = 2)
    private String EXP = "12";

//    @NotBlank(message = "Введите правильный год истечения срока вашей карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильный данные года")
//    @Size(min = 4, max = 4)
    private String EXP_YEAR ="2027";

//    @NotBlank(message = "Введите правильный код на обратной стороне карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильный код")
    private String CVC2 = "212";

    @Positive
    @Min(1)
    private int amount;
    private int residual;
    private TransactionStatus status;
    private TransactionType type;


    private List<TransactionDTO> transactions = new ArrayList<> (  );
    private String orderId;

    public static OrderDTO from(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderId(order.getOrderId ())
                .shopName(order.getShopName())
                .userName(order.getUserName())
                .email(order.getEmail())
                .date(order.getDate())
                .cardHolderName(order.getCardHolderName())
                .CARD (order.getCARD())
                .EXP(order.getEXP())
                .EXP_YEAR(order.getEXP_YEAR())
                .CVC2(order.getCVC2())
                .amount(order.getAmount())
                .residual(order.getResidual())
                .status(order.getStatus())
                .transactions(order.getTransactions().stream().map(TransactionDTO::from).collect(Collectors.toList()))
                .build();
    }
}