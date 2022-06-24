package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Products, Integer> {
    List<Products> findAllByOrderByProductIdAsc();

    List<Products> findByCategories(CategoryEnum categoryEnum);

    List<Products> findByProductName(String productName);
}

