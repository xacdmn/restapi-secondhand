package com.finalproject.secondhand.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.finalproject.secondhand.enums.EStatusProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer productId;
    @Column
    private String productName;
    @Column
    private String category;
    @Column
    private String price;
    @Column
    private String description;
    @Column
    private String image1;
    @Column
    private String image2;
    @Column
    private String image3;
    @Column
    private String image4;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private EStatusProduct statusProduct= EStatusProduct.DIBUAT;

}
