package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Category;
import com.finalproject.secondhand.enums.ECategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByECategory(ECategory eCategory);

}