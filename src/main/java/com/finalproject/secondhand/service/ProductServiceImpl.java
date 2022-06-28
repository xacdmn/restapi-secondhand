package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.enums.CategoryEnum;
import com.finalproject.secondhand.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    public void save(ProductDto productDto) {
        Products products = new Products(productDto);
//        if (productDto.getCategories().isEmpty()) {
//            products.getCategories().add(
//                    categorieService.findByCategory(CategoryEnum.RUMAH).orElseThrow(() ->
//                            new RuntimeException("Error: No category 'RUMAH' Found"))
//            );
//        } else {
//            logger.info(categorieService.findAll().toString());
//            productDto.getCategories().forEach(category -> products.getCategories().add(
//                    categorieService.findByCategory(getEnumIgnoreCase(CategoryEnum.class, category)).orElseThrow(() ->
//                            new RuntimeException("Error: No category '" + category + "' Found. Use `RUMAH` as default."))
//            ));
//        }
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
    public List<Products> findByCategories(CategoryEnum categoryEnum) {
        return productRepository.findByCategories(categoryEnum);
    }

    @Override
    public boolean delete(Integer productId) {
        if (productRepository.findById(productId).isPresent()) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ProductDto productDto) {
//        if (productRepository.findById(productDto.getId()).isPresent()) {
//            productRepository.save(new Products(productDto));
//            return true;
//        }
        return false;
    }
}

