package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.dto.product.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String price;
    private String description;
    private String seller;
    private String city;
    private String productImage;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Categories> categories = new HashSet<>();

    public Products(ProductDto productDto) {
        productName = productDto.getProductName();
        price = productDto.getPrice();
        description = productDto.getDescription();
        seller= productDto.getSeller();
        city = productDto.getCity();
        productImage = productDto.getProductImage().toString();
    }

    public Products(){}

}
