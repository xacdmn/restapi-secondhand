package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    void save(Products products);

    List<Products> findAll();
    List<Products> findByProductName(String productName);

    boolean update(ProductDto productDto);
    boolean delete(Integer productId);

}

