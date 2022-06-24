package com.finalproject.secondhand.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

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
    @NotNull
    private String productName;
    @NotNull
    private Set<String> categories = new HashSet<>();
    @NotNull
    private String price;
    @NotNull
    @JsonIgnore
    private MultipartFile productImage;
    @NotNull
    private String description;
    @NotNull
    private String seller;
    @NotNull
    private String city;
}
