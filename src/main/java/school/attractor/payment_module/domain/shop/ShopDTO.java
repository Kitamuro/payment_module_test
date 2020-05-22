package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.BusinessActivity;
import school.attractor.payment_module.domain.commersant.Commersant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {

    private int id;


    private Commersant commersant;


    private String activity;


    private String site;


    private String registerEmail;


    private String account;


    private String index;


    private String region;


    private String locality;


    private String street;


    private String building;


    private String house;


    private String office;


    private String phoneForCustomer;


    private String emailForCustomer;


    private String contactEmail;
    private String contactName;
    private String contactPhone;
//
}
