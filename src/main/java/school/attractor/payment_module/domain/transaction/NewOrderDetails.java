package school.attractor.payment_module.domain.transaction;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import school.attractor.payment_module.domain.shop.Shop;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderDetails {

//    @NotNull
//    @Positive
    private Integer orderId;

//    @NotBlank
//    @NotEmpty
//    @Size(min = 2, max = 20)
//    private String shopName;

//    private Shop shop;

    private Integer shopId;

//    private TransactionType type;

//    @Positive
//    @Min(1)
//    @Max(10000000)
    private int amount;

//    @Email
//    @NotBlank
    private String email;

//    @NotBlank
//    @NotEmpty
//    @Size(min = 2, max = 30)
    private String userName;

//    @NotBlank
//    @NotEmpty
    private String phone;

//    @NotNull
    private long card;

//    @NotBlank
//    @NotEmpty
//    @NotNull
    private String cardHolderName;

//    @NotNull
//    @Positive
    private Integer cvc2;

//    @NotNull
//    @Positive
    private Integer exp;

//    @NotNull
    private Integer exp_year;

}
