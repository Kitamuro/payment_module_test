package school.attractor.payment_module.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {
    private String id;
    private String name;
    private double price;
    private int quantity;
}
