package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.response.ProductResponse;
import com.finalproject.secondhand.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Homepage", description = "API for access homepage")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/homepage/")
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
public class HomepageController {

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

    @Operation(summary = "Show all products sort and filter")
    @GetMapping("/get-product-page")
    public ResponseEntity<Map<String, Object>> getAllProductPage(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page - 1, size, Sort.by("price"));

            Page<Products> productPage = productService.getAllProductPageByProductNameAndProductCategory(productName, category, paging);
            List<Products> products = productPage.getContent();
            Map<String, Object> response = new HashMap<>();
            List<ProductResponse> productResponse =
                    products.stream().map(ProductResponse::new).collect(
                            Collectors.toList());
            response.put("products", productResponse);
            response.put("currentPage", productPage.getNumber() + 1);
            response.put("totalProducts", productPage.getTotalElements());
            response.put("totalPages", productPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
