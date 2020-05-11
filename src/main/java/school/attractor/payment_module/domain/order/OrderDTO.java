package school.attractor.payment_module.domain.order;

import lombok.*;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionStatus;
import school.attractor.payment_module.domain.transaction.TransactionType;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String card;

    //    @NotBlank(message = "Введите правильный месяц истечения срока вашей карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильные данные месяца")
//    @Size(min = 1, max = 2)
    private String exp;

    //    @NotBlank(message = "Введите правильный год истечения срока вашей карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильный данные года")
//    @Size(min = 4, max = 4)
    private String exp_year;

    //    @NotBlank(message = "Введите правильный код на обратной стороне карты")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Вы ввели неправильный код")
    private String cvc2;

    @Positive
    @Min(1)
    private int amount;
    private int residual;
    private TransactionStatus status;
    private TransactionType type;
    private List<TransactionDTO> transactions = new ArrayList<>();
    private String orderId;


    public static OrderDTO from(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .shopName(order.getShopName())
                .userName(order.getUserName())
                .email(order.getEmail())
                .date(order.getDate())
                .cardHolderName(order.getCardHolderName())
                .card(order.getCard())
                .exp(order.getExp())
                .exp_year(order.getExp_year())
                .cvc2(order.getCvc2())
                .amount(order.getAmount())
                .residual(order.getResidual())
                .status(order.getStatus())
                .transactions(order.getTransactions().stream().map(TransactionDTO::from).collect(Collectors.toList()))
                .build();
    }
}