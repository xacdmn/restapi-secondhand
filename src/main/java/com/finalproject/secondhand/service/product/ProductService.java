package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.response.CustomResponse;
import com.finalproject.secondhand.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    List<Products> findAll();
    ProductResponse findById(Integer productId);
    void save(Products body);
    Products update(Products body, Integer productId);
    Products deleteImage(Products body, Integer productId, Integer n);
    CustomResponse deleteProduct(Integer productId);

}