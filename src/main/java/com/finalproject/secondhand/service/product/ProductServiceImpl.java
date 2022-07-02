package com.finalproject.secondhand.service.product;

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
    public List<Products> findAll() {
        return productRepository.findAllByOrderByProductIdAsc();
    }

    @Override
    public List<Products> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public void save(Products body) {
        Products products = new Products();
        products.setProductName(body.getProductName());
        products.setCategory(body.getCategory());
        products.setPrice(body.getPrice());
        products.setDescription(body.getDescription());
        products.setImage1(body.getImage1());
        products.setImage2(body.getImage2());
        products.setImage3(body.getImage3());
        products.setImage4(body.getImage4());
        products.setUsers(body.getUsers());
        products.setStatusProduct(products.getStatusProduct());
        productRepository.save(products);
    }

    @Override
    public Products update(Products body, Integer productId) {
        Products products = productRepository.getById(productId);
        if (body.getProductName() != null) {
            products.setProductName(body.getProductName());
        }
        if (body.getPrice() != null) {
            products.setPrice(body.getPrice());
        }
        if (body.getDescription() != null) {
            products.setDescription(body.getDescription());
        }
        products.setImage1(body.getImage1());
        products.setImage2(body.getImage2());
        products.setImage3(body.getImage3());
        products.setImage4(body.getImage4());
        return productRepository.save(products);
    }

    @Override
    public Products deleteImage(Products body, Integer productId, Integer n) {
        Products products = productRepository.getById(productId);
        switch (n){
            case 1: {
                products.setImage1(body.getImage1());
            }
            case 2: {
                products.setImage2(body.getImage2());
            }
            case 3: {
                products.setImage3(body.getImage3());
            }
            case 4: {
                products.setImage4(body.getImage4());
            }
        }
        return productRepository.save(products);
    }

    @Override
    public void deleteProduct(Integer productId) {
        Products products = productRepository.getById(productId);
        productRepository.delete(products);
    }
}

