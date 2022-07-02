package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.product.AddProductDto;
import com.finalproject.secondhand.dto.product.UpdateProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.image.CloudinaryStorageService;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Operation(summary = "List all product")
    @GetMapping("find-all")
    public ResponseEntity<List<Products>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Add product")
    @PostMapping(value = "add",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addProduct(@ModelAttribute AddProductDto add, @RequestParam (required = false) List<MultipartFile> imageProfil, Authentication authentication) {
        String username = authentication.getName();
        Users users = userService.findByUsername(username);
        Products products = new Products();
        List<String> urlImage = new ArrayList<>();
        for (int i = 0; i < imageProfil.size(); i++) {
            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(imageProfil.get(i)).getData();
            urlImage.add(i, uploadImage.get("url").toString());
            LOGGER.info(String.valueOf(imageProfil));
            if (urlImage.get(i) == null) {
                LOGGER.info("skip upload...");
            } else {
                if (products.getImage1() == null) {
                    products.setImage1(urlImage.get(i));
                } else if (products.getImage2() == null) {
                    products.setImage2(urlImage.get(i));
                } else if (products.getImage3() == null) {
                    products.setImage3(urlImage.get(i));
                } else if (products.getImage4() == null) {
                    products.setImage4(urlImage.get(i));
                }
            }
        }
        products.setUsers(users);
        products.setCategory(add.getCategory());
        products.setProductName(add.getProductName());
        products.setPrice(add.getPrice());
        products.setDescription(add.getDescription());
        productService.save(products);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }

    @Operation(summary = "Edit product by productId")
    @PutMapping(value = "update/{productId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Products> updateProduct(@ModelAttribute UpdateProductDto update, @RequestParam (required = false) List<MultipartFile> imageProfil,
                                                  @PathVariable Integer productId){
        Products products = new Products();
        List<String> urlImage = new ArrayList<>();
        for (int i = 0; i < imageProfil.size(); i++) {
            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(imageProfil.get(i)).getData();
            urlImage.add(i, uploadImage.get("url").toString());
            LOGGER.info(String.valueOf(imageProfil));
            if (urlImage.get(i) == null) {
                LOGGER.info("skip upload...");
            } else {
                if (products.getImage1() == null) {
                    products.setImage1(urlImage.get(i));
                } else if (products.getImage2() == null) {
                    products.setImage2(urlImage.get(i));
                } else if (products.getImage3() == null) {
                    products.setImage3(urlImage.get(i));
                } else if (products.getImage4() == null) {
                    products.setImage4(urlImage.get(i));
                }
            }
        }
        products.setProductName(update.getProductName());
        products.setPrice(update.getPrice());
        products.setDescription(update.getDescription());
        return new ResponseEntity<>(productService.update(products, productId), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete image by productId by image ke - n")
    @PutMapping("delete/{productId}/{n}")
    public ResponseEntity<Products> deleteImage(@PathVariable Integer productId, @PathVariable Integer n){
        Products products = new Products();
        switch (n){
            case 1: {
                cloudinaryStorageService.delete(products.getImage1());
            }
            case 2: {
                cloudinaryStorageService.delete(products.getImage2());
            }
            case 3: {
                cloudinaryStorageService.delete(products.getImage3());
            }
            case 4: {
                cloudinaryStorageService.delete(products.getImage4());
            }
        }
        return new ResponseEntity<>(productService.deleteImage(products, productId, n), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete product by productId")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
