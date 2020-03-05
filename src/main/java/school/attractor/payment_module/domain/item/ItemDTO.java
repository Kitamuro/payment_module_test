package school.attractor.payment_module.domain.item;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private String id;
    private String name;
    private double price;
    private int quantity;

    static  ItemDTO from(Item item) {
        return   builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
