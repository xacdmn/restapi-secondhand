package com.finalproject.secondhand.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer categoryId;
    private String image;
    private String name;
    private Long price;
    private String description;
}
