package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.enums.ECategory;
import com.finalproject.secondhand.repository.ProductRepository;
import com.finalproject.secondhand.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    public void save(Products body) {
        Products products = new Products();
        products.setProductName(body.getProductName());
        products.setPrice(body.getPrice());
        products.setDescription(body.getDescription());
        products.setImage1(body.getImage1());
        products.setImage2(body.getImage2());
        products.setImage3(body.getImage3());
        products.setImage4(body.getImage4());
        products.setUsers(body.getUsers());
        productRepository.save(products);
    }

    @Override
    public List<Products> findAll() {
        return productRepository.findAllByOrderByProductIdAsc();
    }

    @Override
    public List<Products> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public boolean delete(Integer productId) {
        return false;
    }

    @Override
    public boolean update(ProductDto productDto) {
        return false;
    }
}

