package com.finalproject.secondhand.dto.product;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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
    private List<MultipartFile> imageProfil;
}
