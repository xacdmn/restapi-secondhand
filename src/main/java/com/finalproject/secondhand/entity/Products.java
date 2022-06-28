package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.dto.product.ProductDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private BigDecimal price;
    private String description;
    private Boolean status;

    @NotBlank
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Categories> categories = new HashSet<>();

}
