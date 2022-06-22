package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.repository.CategorieRepository;
import com.finalproject.secondhand.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Products addProduct(Products products) {
        products.setName(products.getName());
        products.setImage(products.getImage());
        products.setPrice(products.getPrice());
        products.setDescription(products.getDescription());
        products.setCategoryId(products.getCategoryId());
        return productRepository.save(products);
    }

    @Override
    public Products updateProduct(Products products, Integer id) {
        Products update = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found!"));
        update.setName(products.getName());
        update.setCategoryId(products.getCategoryId());
        update.setImage(products.getImage());
        update.setPrice(products.getPrice());
        update.setDescription(products.getDescription());
        return productRepository.save(update);
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

