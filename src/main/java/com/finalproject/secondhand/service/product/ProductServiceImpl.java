package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.EStatusResponse;
import com.finalproject.secondhand.repository.ProductRepository;
import com.finalproject.secondhand.repository.UserRepository;
import com.finalproject.secondhand.response.CustomResponse;
import com.finalproject.secondhand.response.HistoryProductResponse;
import com.finalproject.secondhand.response.ProductResponse;
import com.finalproject.secondhand.response.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Products> showAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Products> showProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Products> showProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Page<Products> getAllProductPageByProductNameAndProductCategory(String productName, String category, Pageable pageable) {
        if (productName == null && category == null){
            return productRepository.findAll(pageable);
        } else if (productName == null) {
            return productRepository.findByCategoryContaining(category, pageable);
        } else if (category == null) {
            return productRepository.findByProductNameContaining(productName, pageable);
        } else {
            return productRepository.findByProductNameContainingAndCategoryContainingAndIsPublished(productName, category, pageable, true);
        }
    }

    @Override
    public String validasiProfil(String username) {
        Users validasi = userRepository.findByUsername(username);
        if (validasi.getFullname().length() > 0) {
            if (validasi.getCity().length() > 0) {
                if (validasi.getAddress().length() > 0) {
                    if (validasi.getPhone().length() > 0) {
                        return "User profil is complete";
                    }
                }
            }
        }
        return "User profil not complete";
    }


    @Override
    public Products findProductById(Integer productId) {
        return productRepository.getById(productId);
    }

    @Override
    public ProductResponse findByProductId(Integer productId) {
        Products products = productRepository.getById(productId);
        return new ProductResponse(products);
    }

    @Override
    public List<Products> findProductByUser(String username) {
        Users users = userRepository.findByUsername(username);
        return productRepository.findByUsers(users);
    }

    @Override
    public List<Products> findProductByUserByIsSold(String username) {
        Users users = userRepository.findByUsername(username);
        Boolean isSold = true;
        return productRepository.findByUsersAndIsSold(users, isSold);
    }

    @Override
    public List<WishlistResponse> findProductByWishlist() {
        return null;
    }

    @Override
    public List<HistoryProductResponse> findProductByIsSold() {
        return null;
    }

    @Override
    public CustomResponse save(Products body) {
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
        products.setIsPublished(body.getIsPublished());
        products.setIsSold(body.getIsSold());
        if (products.getIsPublished().equals(true)){
            productRepository.save(products);
            return new CustomResponse(
                    "Product published successfully",
                    EStatusResponse.SUCCESS.getName());
        } else {
            productRepository.save(products);
            return new CustomResponse(
                    "Product not published",
                    EStatusResponse.SUCCESS.getName());
        }
    }

    @Override
    public void publish(Products body, Integer productId) {
        Products products = productRepository.getById(productId);
        products.setIsPublished(body.getIsPublished());
        productRepository.save(products);
    }

    @Override
    public void update(Products body, Integer productId) {
        Products products = productRepository.getById(productId);
        if (body.getProductName() != null) {
            if (body.getProductName().length() > 0) {
                products.setProductName(body.getProductName());
            }
        }
        if (body.getPrice() != null) {
            if (body.getPrice().length() > 0) {
                products.setPrice(body.getPrice());
            }
            products.setPrice(body.getPrice());
        }
        if (body.getDescription() != null) {
            if (body.getDescription().length() > 0) {
                products.setDescription(body.getDescription());
            }
        }
        if (products.getImage1() != null) {
            products.setImage1(body.getImage1());

        }
        if (products.getImage2() != null) {
            products.setImage2(body.getImage2());

        }
        if (products.getImage3() != null) {
            products.setImage3(body.getImage3());

        }
        if (products.getImage4() != null) {
            products.setImage4(body.getImage4());
        }
        productRepository.save(products);
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
    public CustomResponse deleteProduct(Integer productId) {
        Products products = productRepository.getById(productId);
        if (products.getProductId() == null){
            return new CustomResponse(
                    "Product is not present",
                    EStatusResponse.NOT_FOUND.getName());
        } else {
            productRepository.delete(products);
            return new CustomResponse(
                    "Product has been removed from store",
                    EStatusResponse.SUCCESS.getName());
        }
    }
}

