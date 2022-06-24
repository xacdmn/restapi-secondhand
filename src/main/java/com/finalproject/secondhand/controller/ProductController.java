//package com.finalproject.secondhand.controller;
//
//import com.finalproject.secondhand.dto.product.ProductDto;
//import com.finalproject.secondhand.entity.Products;
//import com.finalproject.secondhand.service.ProductService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RestController
//@RequiredArgsConstructor
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    ModelMapper modelMapper;
//
//    @Operation(summary = "Add product")
//    @PostMapping("/api/product/add")
//    public ResponseEntity<Products> addProduct(@RequestBody ProductDto create){
//        Products products = modelMapper.map(create, Products.class);
//        return new ResponseEntity<>(productService.addProduct(products), HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Edit product by id")
//    @PutMapping("/api/product/update/{id}")
//    public ResponseEntity<Products> updateProduct(@RequestBody ProductDto update, @PathVariable Integer id){
//        Products products = modelMapper.map(update, Products.class);
//        return new ResponseEntity<>(productService.updateProduct(products, id), HttpStatus.ACCEPTED);
//    }
//
//    @Operation(summary = "Find all product")
//    @GetMapping("/api/product/get")
//    public ResponseEntity<List<Products>> getAllUser(){
//        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Delete product by id")
//    @DeleteMapping("/api/product/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
//        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.ACCEPTED);
//    }
//}
