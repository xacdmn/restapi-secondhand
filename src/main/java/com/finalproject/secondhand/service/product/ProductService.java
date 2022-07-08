package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.response.CustomResponse;
import com.finalproject.secondhand.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    List<Products> showAllProduct();
    Page<Products> getAllProductPageByProductNameAndProductCategory(String productName, String productCategory, Pageable pageable);

    String validasiProfil(String username);

    Products findProductById(Integer productId);
    ProductResponse findByProductId(Integer productId);

    ProductResponse save(Products body);
    void publish(Products body, Integer productId);
    void update(Products body, Integer productId);
    Products deleteImage(Products body, Integer productId, Integer n);
    CustomResponse deleteProduct(Integer productId);

}