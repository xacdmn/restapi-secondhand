package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.response.ProductResponse;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "History", description = "API for access get history user")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/history/")
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
public class HistoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

//    @Operation(summary = "Find History All Product by User")
//    @GetMapping("/product")
//    public ResponseEntity<List<ProductResponse>> findProductByUser(Authentication valid) {
//        String username = valid.getName();
//        Users users = userService.findByUsername(username);
//        productService.findProductByUser(users);
//        return new ResponseEntity<>(, HttpStatus.OK);
//    }
}
