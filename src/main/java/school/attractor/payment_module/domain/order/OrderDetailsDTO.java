package school.attractor.payment_module.domain.order;

import lombok.*;
import school.attractor.payment_module.domain.shop.Shop;
import school.attractor.payment_module.domain.shop.ShopDTO;
import school.attractor.payment_module.domain.transaction.TransactionStatus;
import school.attractor.payment_module.domain.transaction.TransactionType;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private int id;

    private ShopDTO shopDTO;

    private String userName;

    private String email;

    private String phone;

    private Date date;

    private String cardHolderName;

    private String card;

    private int amount;

    private int residual;

    private TransactionStatus status;

    private TransactionType type;

    private Integer orderId;

    private List<OrderTransactionList> transactions;


    public static OrderDetailsDTO from(Order order) {
        return OrderDetailsDTO.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .userName(order.getUserName())
                .email(order.getEmail())
                .date(order.getDate())
                .cardHolderName(order.getCardHolderName())
                .card( maskCardNumber(order.getCard(), "####********####"))
                .amount(order.getAmount())
                .residual(order.getResidual())
                .status(order.getStatus())
                .transactions(order.getTransactions().stream().map(OrderTransactionList::from).collect(Collectors.toList()))
                .build();
    }

    public static OrderDetailsDTO from(OrderDTO order) {
        return OrderDetailsDTO.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .shopDTO(order.getShopDTO ())
                .userName(order.getUserName())
                .email(order.getEmail())
                .date(order.getDate())
                .cardHolderName(order.getCardHolderName())
                .card( maskCardNumber(order.getCard(), "####********####"))
                .amount(order.getAmount())
                .residual(order.getResidual())
                .status(order.getStatus())
                .transactions(order.getTransactions().stream().map(OrderTransactionList::from).collect(Collectors.toList()))
                .build();
    }

    public static String maskCardNumber(long cardNumber, String mask) {
        String cardNumberString = String.valueOf(cardNumber);
        int index = 0;

        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumberString.charAt(index));
                index++;
            } else if (c == 'x') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }
        return maskedNumber.toString();
    }
}
