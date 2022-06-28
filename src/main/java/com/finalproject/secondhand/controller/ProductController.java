package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.service.CloudinaryStorageService;
import com.finalproject.secondhand.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Operation(summary = "Add product")
    @PostMapping(value = "/api/product/add",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addProduct(@ModelAttribute ProductDto productDto){
        Map<?, ?> uploadImage1 = (Map<?, ?>) cloudinaryStorageService.upload(productDto.getImage1()).getData();
        Map<?, ?> uploadImage2 = (Map<?, ?>) cloudinaryStorageService.upload(productDto.getImage2()).getData();
        Map<?, ?> uploadImage3 = (Map<?, ?>) cloudinaryStorageService.upload(productDto.getImage3()).getData();
        Map<?, ?> uploadImage4 = (Map<?, ?>) cloudinaryStorageService.upload(productDto.getImage4()).getData();
        Products products = new Products();
        products.setProductName(productDto.getProductName());
        products.setDescription(productDto.getDescription());
        products.setPrice(productDto.getPrice());
        products.setImage1(uploadImage1.get("url").toString());
        products.setImage2(uploadImage2.get("url").toString());
        products.setImage3(uploadImage3.get("url").toString());
        products.setImage4(uploadImage4.get("url").toString());
        productService.save(productDto);
        return new ResponseEntity<>("Tambah produk berhasil", HttpStatus.CREATED);
    }

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
}
