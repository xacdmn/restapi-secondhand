package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Products, Integer> {
    List<Products> findAllByOrderByProductIdAsc();

    List<Products> findByProductName(String productName);

    Page<Products> findAll(Pageable pageable);

    Page<Products> findByProductNameContainingIgnoreCaseAndCategoryContainingAndPriceBetween(
            String productName, String category, BigInteger priceMin,
            BigInteger priceMax, Pageable pageable);

}