package com.finalproject.secondhand.dto.categorie;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieDto {

    @NotBlank(message = "Nama Kategori harus diisi!")
    private String categoryName;

}
