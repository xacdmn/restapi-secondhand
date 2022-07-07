package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.EStatusResponse;
import com.finalproject.secondhand.response.CustomResponse;
import com.finalproject.secondhand.response.ProductResponse;
import com.finalproject.secondhand.service.image.CloudinaryStorageService;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product", description = "API for processing CRUD Products")
@RequestMapping("/api/product/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Operation(summary = "Find product by productId")
    @GetMapping("{productId}")
    public ResponseEntity<ProductResponse> findProductById(Integer productId) {
        return new ResponseEntity<>(productService.findByProductId(productId), HttpStatus.OK);
    }

    @Operation(summary = "Preview product")
    @PostMapping(value = "add/{isPublished}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductResponse> previewProduct(@RequestParam (name = "productName") String productName,
                                        @RequestParam (name = "category") String category,
                                        @RequestParam (name = "price") String price,
                                        @RequestParam (name = "description") String description,
                                        @RequestParam (required = false, name = "image") List<MultipartFile> image,
                                        @PathVariable String isPublished,
                                        Authentication authentication) {
        Products products = new Products();
        if (isPublished.equals("preview")){
            products.setIsPublished(products.getIsPublished());
        } else if (isPublished.equals("publish")) {
            products.setIsPublished(true);
        }
        String username = authentication.getName();
        Users users = userService.findByUsername(username);
        products.setUsers(users);
        products.setProductName(productName);
        products.setCategory(category);
        products.setPrice(price);
        products.setDescription(description);
        products.setIsSold(products.getIsSold());
        List<String> urlImage = new ArrayList<>();
        if (image == null) {
            LOGGER.info("skip upload...");
        }else {
            for (int i = 0; i < image.size(); i++) {
                Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(image.get(i)).getData();
                urlImage.add(i, uploadImage.get("url").toString());
                LOGGER.info(String.valueOf(image));
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
        }
        return new ResponseEntity<>(productService.save(products), HttpStatus.CREATED);
    }

    @Operation(summary = "Publish product")
    @PutMapping("update/publish/{productId})")
    public ResponseEntity<?> publishProduct(@PathVariable Integer productId){
        Products products = new Products();
        products.setIsPublished(true);
        productService.publish(products, productId);
        return new ResponseEntity<>("Product published successfully", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Edit product by productId")
    @PutMapping(value = "update/{productId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateProduct(@RequestParam (required = false, name = "productName") String productName,
                                                  @RequestParam (required = false, name = "price") String price,
                                                  @RequestParam (required = false, name = "description") String description,
                                                  @RequestParam (required = false) List<MultipartFile> image,
                                                  @PathVariable Integer productId){
        Products products = new Products();
        products.setProductName(productName);
        products.setPrice(price);
        products.setDescription(description);
        List<String> urlImage = new ArrayList<>();
        if (image == null) {
            LOGGER.info("skip upload image");
        } else {
            for (int i = 0; i < image.size(); i++) {
                Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(image.get(i)).getData();
                urlImage.add(i, uploadImage.get("url").toString());
                LOGGER.info(String.valueOf(image));
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
        }
        productService.update(products, productId);
        return new ResponseEntity<>("Product is updated", HttpStatus.ACCEPTED);
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
    public ResponseEntity<CustomResponse> deleteProduct(@PathVariable Integer productId) {

        CustomResponse response = productService.deleteProduct(productId);

        boolean isNotFound = response
                .getStatus()
                .equals(EStatusResponse.NOT_FOUND.getName());
        if (isNotFound) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
