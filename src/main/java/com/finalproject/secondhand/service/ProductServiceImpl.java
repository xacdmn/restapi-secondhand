package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Products addProduct(Products products) {
        products.getName();
        products.getImage();
        products.getPrice();
        products.getDescription();
        products.getCategoryId();
        return productRepository.save(products);
    }

    @Override
    public Products updateProductByName(Products product) {
        Products update = productRepository.findProductsByName(product.getName());
        update.setName(product.getName());
        update.setCategoryId(product.getCategoryId());
        update.setImage(product.getImage());
        update.setPrice(product.getPrice());
        update.setDescription(product.getDescription());
        return productRepository.save(product);
    }

    @Override
    public void deleteByName(String name) {
        productRepository.deleteProductsByName(name);
    }

    @Override
    public List<Products> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Products> getProductByCategoryName(String category) {
        return null;
    }

    @Override
    public List<Products> getProductByName(String name) {
        return null;
    }
}

