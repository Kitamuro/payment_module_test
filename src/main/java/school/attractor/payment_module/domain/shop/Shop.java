package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.BusinessActivity;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.order.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "commersant_id")
    private Commersant commersant;

    @Column
    private int hold;

    @Column
    private BusinessActivity activity;

    @Column
    private String siteName;

    @Column
    private String registerEmail;

    @Column
    private String account;

    @Column
    private String index;

    @Column
    private String region;

    @Column
    private String locality;

    @Column
    private String street;

    @Column
    private String building;

    @Column
    private String house;

    @Column
    private String office;

    @Column
    private String phoneForCustomer;

    @Column
    private String emailForCustomer;

    @Column
    private String contactEmail;

   @Column
    private String contactName;
   @Column
    private String contactPhone;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "shop")
    private List<Order> orders;


    public static Shop from(ShopDTO shopDTO) {
        return  Shop.builder()
                .commersant ( shopDTO.getCommersant () )
                .activity(shopDTO.getActivity ())
                .siteName(shopDTO.getSiteName ())
                .registerEmail(shopDTO.getRegisterEmail ())
                .account(shopDTO.getAccount ())
                .index(shopDTO.getIndex ())
                .region(shopDTO.getRegion ())
                .locality(shopDTO.getLocality ())
                .street(shopDTO.getStreet ())
                .building(shopDTO.getBuilding ())
                .house(shopDTO.getHouse ())
                .office(shopDTO.getOffice ())
                .phoneForCustomer(shopDTO.getPhoneForCustomer ())
                .emailForCustomer(shopDTO.getEmailForCustomer ())
                .contactEmail(shopDTO.getContactEmail ())
                .contactName(shopDTO.getContactName ())
                .contactPhone(shopDTO.getContactPhone ())
                .build ();
    }


}
