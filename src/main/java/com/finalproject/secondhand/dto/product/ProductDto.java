package com.finalproject.secondhand.dto.product;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull
    private String productName;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
    private MultipartFile image4;
    @NotNull
    private Boolean status;
}
