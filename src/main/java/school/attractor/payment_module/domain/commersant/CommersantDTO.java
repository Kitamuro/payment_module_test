package school.attractor.payment_module.domain.commersant;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommersantDTO {
    private String id;
    private String name;

    public static CommersantDTO from(Commersant commersant) {
        return  builder()
                .id(commersant.getId())
                .name(commersant.getName())
                .build();
    }
}
