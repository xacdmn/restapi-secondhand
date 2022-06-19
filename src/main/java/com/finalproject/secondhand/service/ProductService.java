package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Products;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface ProductService {

    Products addProduct(Products products);
    Products updateProduct(Products product, Integer id);
    String deleteById(Integer id);
    List<Products> getAllProduct();
    List<Products> getProductByName(String name);

}

