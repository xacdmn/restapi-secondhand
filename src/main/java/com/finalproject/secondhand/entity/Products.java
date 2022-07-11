package com.finalproject.secondhand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity(name = "products")
@EqualsAndHashCode(callSuper = true)
public class Products extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private String price;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "image3")
    private String image3;
    @Column(name = "image4")
    private String image4;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @JsonIgnore
//    private List<Notifications> notifications;
    @Column
    private Boolean isPublished= false;
    @Column
    private Boolean isSold= false;

}
