package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Categories;
import com.finalproject.secondhand.enums.ECategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CategorieService {

    Optional<Categories> findByCategory(ECategory category);

    List<Categories> findAll();

    void save(Categories categories);

}
