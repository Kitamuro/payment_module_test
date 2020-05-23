package school.attractor.payment_module.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.BusinessActivity;
import school.attractor.payment_module.domain.commersant.Commersant;

import javax.persistence.*;

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
    private BusinessActivity activity;

    @Column
    private String site;

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


//








}
