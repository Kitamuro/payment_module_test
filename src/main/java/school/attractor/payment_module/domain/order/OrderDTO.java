package school.attractor.payment_module.domain.order;

import lombok.*;
import school.attractor.payment_module.domain.transaction.TransactionDTO;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDTO {
    private int id;
    private String shopName;
    private String userName;
    private String email;
    private Date date;
    private String cardHolderName;
    private String CARD;
    private String EXP;
    private String EXP_YEAR;
    private String CVC2;
    private int amount;
    private int residual;
    private TransactionStatus status;
    private List<TransactionDTO> transactions;

    public static OrderDTO from(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .shopName(order.getShopName())
                .userName(order.getUserName())
                .email(order.getEmail())
                .date(order.getDate())
                .cardHolderName(order.getCardHolderName())
                .CARD(order.getCARD())
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