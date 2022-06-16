package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Products addProduct(Products products);
    Products updateProductByName(Products product);
    void deleteByName(String name);
    List<Products> getAllProduct();
    List<Products> getProductByCategoryName(String category);
    List<Products> getProductByName(String name);

}

