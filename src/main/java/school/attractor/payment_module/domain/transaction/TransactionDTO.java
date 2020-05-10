package school.attractor.payment_module.domain.transaction;

import lombok.*;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.item.ItemDTO;
import school.attractor.payment_module.domain.order.OrderDTO;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Builder
@Data
public class TransactionDTO {
    private String id;
    private CommersantDTO commersantDTO;
    private String shopId;

    private String userName;

    private String email;

    private String phone;

    private String cardHolderName;

    private String CARD;

    private String EXP;

    private String EXP_YEAR;

    private String CVC2;

    private List<ItemDTO> items;

    private OrderDTO order;

    private Integer amount;

    private String shopName;

    private TransactionType type;

    private TransactionStatus status;

    private Date date;

    public static TransactionDTO from(Transaction transaction) {
        return builder()
                .amount(transaction.getAmount())
                .shopId(transaction.getShopId())
                .shopName(transaction.getShopName())
                .userName(transaction.getUserName())
                .email(transaction.getEmail())
                .phone(transaction.getPhone())
                .cardHolderName(transaction.getCardHolderName())
                .CARD(transaction.getCARD())
                .EXP(transaction.getEXP())
                .EXP_YEAR(transaction.getEXP_YEAR())
                .CVC2(transaction.getCVC2())
                .status(transaction.getStatus())
                .order(OrderDTO.from(transaction.getOrder()))
                .type(transaction.getType())
                .date(transaction.getDate()).build();

    }
}
