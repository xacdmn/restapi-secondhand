package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Categories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CategorieService {

    List<Categories> getAllCategorie();
    Categories addCategorie(Categories body);
    Optional<Categories> getCategorieById(Integer categoryId);
    Categories updateCategorie(Categories body, Integer categoryId);
    String deleteCategorie(Integer categoryId);

}
