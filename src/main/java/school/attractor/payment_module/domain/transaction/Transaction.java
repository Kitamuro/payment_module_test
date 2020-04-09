package school.attractor.payment_module.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.item.Item;


import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private String id;
    private Commersant commersant;
    private String shopId;
    private String shopName;
    private String userName;
    private String email;
    private String phone;
    private String cardHolderName;
    private String CARD;
    private String EXP;
    private String EXP_YEAR;
    private String CVC2;
    private String currency;
    private String amount;
    private double fee;
    private Date date;
    private List<Item> items;
    private String status;
    private String orderId;
}
