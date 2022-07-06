package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.repository.ProductRepository;
import com.finalproject.secondhand.response.ProductResponse;
import com.finalproject.secondhand.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Homepage", description = "API for access homepage")
@RequestMapping("/api/homepage/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class HomepageController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Show all product")
    @GetMapping("show-products")
    public ResponseEntity<List<ProductResponse>> allProduct(){
        List<Products> product = productService.showAllProduct();
        List<ProductResponse> productResponse =
                product.stream().map(ProductResponse::new).collect(
                        Collectors.toList());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Show product by category")
    @GetMapping("show-products/{category}")
    public ResponseEntity<List<ProductResponse>> showProductByCategory(@PathVariable (name = "category") String category){
        List<Products> product = productService.showProductByCategory(category);
        List<ProductResponse> productResponse =
                product.stream().map(ProductResponse::new).collect(
                        Collectors.toList());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Show product by productName")
    @GetMapping("show-products/{productName}")
    public ResponseEntity<List<ProductResponse>> showProductByProductName(@PathVariable (name = "productName") String productName){
        List<Products> product = productService.showProductByProductName(productName);
        List<ProductResponse> productResponse =
                product.stream().map(ProductResponse::new).collect(
                        Collectors.toList());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/get-home-page")
    public Page<Products> pagePagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/find-product")
    public Page<Products> findProduct(
            @RequestParam(defaultValue = "", required = false) String productName,
            @RequestParam(defaultValue = "", required = false) String category,
            @RequestParam(defaultValue = "0", required = false) BigInteger priceMin,
            @RequestParam(defaultValue = "9999999999", required = false) BigInteger priceMax,
            @RequestParam(defaultValue = "productName,asc", required = false) String[] sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]));
        }
        return productRepository.findByProductNameContainingIgnoreCaseAndCategoryContainingAndPriceBetween(
                productName, category, priceMax, priceMin, PageRequest.of(page, size, Sort.by(orders)));
    }
}
