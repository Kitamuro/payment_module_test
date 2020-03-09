package school.attractor.payment_module.domain.transaction;

import lombok.*;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.item.ItemDTO;
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
    private String EXP_YEAR;;
    private String CVC2;
    private List<ItemDTO> items;
    private double amount;

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
