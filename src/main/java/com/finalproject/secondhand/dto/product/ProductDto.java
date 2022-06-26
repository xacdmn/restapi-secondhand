package com.finalproject.secondhand.dto.product;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ProductDto {
    private Integer id;
    @NotNull
    private String productName;
    @NotNull
    private Set<String> categories = new HashSet<>();
    @NotNull
    private String price;
    @NotNull
    private String description;
    @NotNull
    private String seller;
    @NotNull
    private String city;
}
