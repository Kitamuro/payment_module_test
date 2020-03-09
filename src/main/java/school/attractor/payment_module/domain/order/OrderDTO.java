package school.attractor.payment_module.domain.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
    String shopName;
    String userName;
    String email;
    String phone;
    String order_id;
    String amount;

}
