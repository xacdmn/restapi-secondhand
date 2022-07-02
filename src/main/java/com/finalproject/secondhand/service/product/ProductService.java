package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Products;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    List<Products> findAll();
    List<Products> findByProductName(String productName);
    void save(Products body);
    Products update(Products body, Integer productId);
    Products deleteImage(Products body, Integer productId, Integer n);
    void deleteProduct(Integer productId);

}