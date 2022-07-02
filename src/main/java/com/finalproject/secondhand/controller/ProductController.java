package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.product.ProductDto;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.ProductRepository;
import com.finalproject.secondhand.service.image.CloudinaryStorageService;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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
    private ProductRepository productRepository;

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
    public ResponseEntity<?> addProduct(@ModelAttribute ProductDto productDto, Authentication authentication) {
        String username = authentication.getName();
        Users users = userService.findByUsername(username);
        Products products = new Products();
        List<String> urlImage = new ArrayList<>();
        for (int i = 0; i < productDto.getImageProfil().size(); i++) {
            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(productDto.getImageProfil().get(i)).getData();
            urlImage.add(i, uploadImage.get("url").toString());
            LOGGER.info(urlImage.get(i));
            if (urlImage.get(i).isEmpty()) {
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
        products.setProductName(productDto.getProductName());
        products.setPrice(productDto.getPrice().toString());
        products.setDescription(productDto.getDescription());
        productService.save(products);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
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
        List<Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Order(Direction.fromString(sort[1]), sort[0]));
        }
        return productRepository.findByProductNameContainingIgnoreCaseAndCategoryContainingAndPriceBetween(
                productName, category, priceMax, priceMin, PageRequest.of(page, size, Sort.by(orders)));
    }
}

//    @Operation(summary = "Edit product by id")
//    @PutMapping("/api/product/update/{id}")
//    public ResponseEntity<Products> updateProduct(@RequestBody ProductDto update, @PathVariable Integer id){
//        Products products = modelMapper.map(update, Products.class);
//        return new ResponseEntity<>(productService.updateProduct(products, id), HttpStatus.ACCEPTED);
//    }

//    @Operation(summary = "Delete product by id")
//    @DeleteMapping("/api/product/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
//        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.ACCEPTED);
//    }
