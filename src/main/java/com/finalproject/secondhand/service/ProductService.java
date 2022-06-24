package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.enums.CategoryEnum;
import com.finalproject.secondhand.repository.CategorieRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    void save(ProductDto productDto);

    List<Products> findAll();

    List<Products> findByProductName(String productName);

    List<Products> findByCategories(CategoryEnum categoryEnum);

    boolean delete(Integer productId);

    boolean update(ProductDto productDto);

}

