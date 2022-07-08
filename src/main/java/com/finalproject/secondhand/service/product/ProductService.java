package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.response.CustomResponse;
import com.finalproject.secondhand.response.HistoryProductResponse;
import com.finalproject.secondhand.response.ProductResponse;
import com.finalproject.secondhand.response.WishlistResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    //Homepage
    List<Products> showAllProduct();
    List<Products> showProductByCategory(String category);
    List<Products> showProductByProductName(String productName);
    Page<Products> getAllProductPageByProductNameAndProductCategory(String productName, String productCategory, Pageable pageable);

    //Validasi klik tombol jual
    String validasiProfil(String username);

    Products findProductById(Integer productId);
    ProductResponse findByProductId(Integer productId);

    //History
    List<Products> findProductByUser(String username);
    List<Products> findProductByUserByIsSold(String username);
    List<WishlistResponse> findProductByWishlist();
    List<HistoryProductResponse> findProductByIsSold();

    // C R U D
    ProductResponse save(Products body);
    void publish(Products body, Integer productId);
    void update(Products body, Integer productId);
    Products deleteImage(Products body, Integer productId, Integer n);
    CustomResponse deleteProduct(Integer productId);

}