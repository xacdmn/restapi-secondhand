package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categories, Integer> {
}
