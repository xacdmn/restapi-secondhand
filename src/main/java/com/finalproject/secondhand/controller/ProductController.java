package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<Products> addProduct(@RequestBody ProductDto create){
        Products products = modelMapper.map(create, Products.class);
        return new ResponseEntity<>(productService.addProduct(products), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProduct(@RequestBody ProductDto update, @PathVariable Integer id){
        Products products = modelMapper.map(update, Products.class);
        return new ResponseEntity<>(productService.updateProduct(products, id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Products>> getAllUser(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.ACCEPTED);
    }
//    public ResponseEntity<Products> addProducts(){};
}
