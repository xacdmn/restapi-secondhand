package com.finalproject.secondhand.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity(name = "products")
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne(targetEntity = Categories.class)
    @JoinColumn(name = "category_id", nullable = false)
    private Categories categoryId;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

}
