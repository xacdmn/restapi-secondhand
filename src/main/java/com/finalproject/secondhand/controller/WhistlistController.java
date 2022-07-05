package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.response.WhistlistResponse;
import com.finalproject.secondhand.service.product.WhistlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Whistlist", description = "API for processing Whistlist")
@RequestMapping("/api/whistlist/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class WhistlistController {

    @Autowired
    WhistlistService whistlistService;

//    @Operation(summary = "Find all wishlist in a product")
//    @PostMapping("find-all")
//    public ResponseEntity<WhistlistResponse> findAll() {
//        return new ResponseEntity<>(whistlistService.findAll(), HttpStatus.OK);
//    }
//
//    @Operation(summary = "add wishlist in product")
//    @PostMapping("add")
//    public ResponseEntity<?> add(Integer productId, Authentication authentication) {
//        String username = authentication.getName();
//        whistlistService.addWhistlist(productId, username);
//        return new ResponseEntity<>("Whistlist added", HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "delete wishlist in product")
//    @PostMapping("delete")
//    public ResponseEntity<?> delete() {
//
//        return new ResponseEntity<>("Whistlist deleted", HttpStatus.OK);
//    }

}
