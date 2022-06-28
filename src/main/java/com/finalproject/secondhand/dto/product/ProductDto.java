package com.finalproject.secondhand.dto.product;

import com.finalproject.secondhand.entity.Users;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ProductDto {
    private Integer id;

    private Users userId;

    @NotNull
    private String productName;

    @NotNull
    private Set<String> categories = new HashSet<>();

    @NotNull
    private BigDecimal price;

    @NotNull
    private String description;

    @NotNull
    private String seller;

    @NotNull
    private String city;

    @NotNull
    private String image1;

    private String image2;

    private String image3;

    private String image4;

    @NotNull
    private Boolean status;
}
