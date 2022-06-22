package com.finalproject.secondhand.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(max = 25)
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    @Size(max = 200)
    private String description;

}
