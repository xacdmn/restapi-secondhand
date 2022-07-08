package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.response.HistoryProductResponse;
import com.finalproject.secondhand.response.WishlistResponse;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.transaction.OfferService;
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
import java.util.stream.Collectors;

@RestController
@Tag(name = "History", description = "API for access get history user")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/history/")
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
public class HistoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OfferService offerService;

    @Operation(summary = "Find History All Product by User")
    @GetMapping("/product-user")
    public ResponseEntity<List<HistoryProductResponse>> findProductByUser(Authentication valid) {
        String username = valid.getName();
        List<Products> products = productService.findProductByUser(username);
        List<HistoryProductResponse> historyProductResponses = products.stream()
                .map(HistoryProductResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(historyProductResponses, HttpStatus.OK);
    }

    @Operation(summary = "Find History All Product by User - wishlist")
    @GetMapping("/product-wishlist")
    public ResponseEntity<List<WishlistResponse>> findProductByUserByWishlist(Authentication valid) {
        String username = valid.getName();
        List<Offers> offers = offerService.findByUser(username);
        List<WishlistResponse> wishlistResponses = offers.stream()
                .map(WishlistResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(wishlistResponses, HttpStatus.OK);
    }

    @Operation(summary = "Find History All Product by User - sold")
    @GetMapping("/product-sold")
    public ResponseEntity<List<HistoryProductResponse>> findProductByUserBySold(Authentication valid) {
        String username = valid.getName();
        List<Products> products = productService.findProductByUserByIsSold(username);
        List<HistoryProductResponse> historyProductResponses = products.stream()
                .map(HistoryProductResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(historyProductResponses, HttpStatus.OK);
    }
}
