package com.finalproject.secondhand.dto.product;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull
    private String productName;
    @NotNull
    private String category;
    @NotNull
    private String price;
    @NotNull
    private String description;

}
