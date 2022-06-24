package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Categories;
import com.finalproject.secondhand.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categories, Integer> {
    Optional<Categories> findByCategory(CategoryEnum category);

}
