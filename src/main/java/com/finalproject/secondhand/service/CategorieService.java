package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Categories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CategorieService {

    List<Categories> getAllKategori();
    Categories postKategori(Categories body);
    Optional<Categories> getKategoriById(Integer categoryId);
    Categories updateKategori(Categories body, Integer categoryId);
    String deleteKategori(Integer categoryId);

}