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

    @ManyToOne
    private Users usersId;

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

    public Products(ProductDto productDto) {
        usersId = productDto.getUserId();
        productName = productDto.getProductName();
        price = productDto.getPrice();
        description = productDto.getDescription();
        status = productDto.getStatus();
        image1 = productDto.getImage1();
        image2 = productDto.getImage2();
        image3 = productDto.getImage3();
        image4 = productDto.getImage4();
//        seller= productDto.getSeller();
//        city = productDto.getCity();
//        productImage = productDto.getProductImage().toString();
    }

    public Products(){}

}
