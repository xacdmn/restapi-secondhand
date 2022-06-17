package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    Products deleteProductsByName(String name);
    Products findByName(String name);
    List<Products> findProductsByName(String name);
}

