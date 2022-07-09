package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.enums.EStatusProcess;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "offers")
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Integer offerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products product;

    @Column
    private String priceNegotiated;

    @Column
    @Enumerated(EnumType.STRING)
    private EStatusProcess statusProcess = EStatusProcess.WAITING;

}
