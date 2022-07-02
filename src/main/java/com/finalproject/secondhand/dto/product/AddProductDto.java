package com.finalproject.secondhand.dto.product;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {

    private @NotNull String productName;
    private @NotNull String category;
    private @NotNull String price;
    private @NotNull String description;

}
