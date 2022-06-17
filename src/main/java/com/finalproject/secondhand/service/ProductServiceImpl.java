package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Products update = productRepository.findByName(product.getName());
        update.setName(product.getName());
        update.setCategoryId(product.getCategoryId());
        update.setImage(product.getImage());
        update.setPrice(product.getPrice());
        update.setDescription(product.getDescription());
        return productRepository.save(product);
    }

    @Override
    public String deleteById(Integer id) {
        productRepository.deleteById(id);
        return "Product "+id+" deleted!";
    }

    @Override
    public List<Products> getAllProduct() {
        return productRepository.findAll();
    }

//    @Override
//    public List<Products> getProductByCategoryName(String category) {
//        return null;
//    }

    @Override
    public List<Products> getProductByName(String name) {
        return productRepository.findProductsByName(name);
    }
}

