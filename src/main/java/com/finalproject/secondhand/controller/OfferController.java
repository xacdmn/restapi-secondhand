package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.EStatusProcess;
import com.finalproject.secondhand.response.OfferResponse;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.transaction.OfferService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Tag(name = "Offer", description = "API for processing Transaction")
@RequestMapping("/api/offer/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
public class OfferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    OfferService offerService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Operation(summary = "Api whatsapp")
    @GetMapping("show-offer/whastapp/{offerId}")
    public ResponseEntity<?> apiWhatsapp(@PathVariable Integer offerId) {
        return new ResponseEntity<>(offerService.whatsappSender(offerId), HttpStatus.OK);
    }

    @Operation(summary = "Show offer by user")
    @GetMapping("show-offer/{offerId}")
    public ResponseEntity<OfferResponse> showOffer(@PathVariable Integer offerId) {
        Offers offers = offerService.findByOfferId(offerId);
        OfferResponse response = new OfferResponse(offers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add offers")
    @PostMapping("add/{productId}")
    public ResponseEntity<?> saveOffer(@RequestPart String priceNegotiated,
                                       @PathVariable (name = "productId") Integer productId,
                                       Authentication valid) {
        Products products = productService.findProductById(productId);
        String username = valid.getName();
        LOGGER.info(priceNegotiated);
        Users users = userService.findByUsername(username);
        if (products.getIsWishlist().equals(false)){
            products.setIsWishlist(true);
            Offers offers = new Offers();
            offers.setUsers(users);
            offers.setProduct(products);
            offers.setPriceNegotiated(priceNegotiated);
            offers.setStatusProcess(offers.getStatusProcess());
            offerService.saveOffer(offers);
            return new ResponseEntity<>("Offer Added", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("product has been offer", HttpStatus.OK);
        }
    }

    @Operation(summary = "Update status offers")
    @PutMapping("update/{offerId}/{status}")
    public ResponseEntity<?> updateStatusProcess(@PathVariable ("offerId") Integer offerId,
                                                  @PathVariable ("status") String status) {
        Offers offers = offerService.findByOfferId(offerId);
        Products products = productService.findProductById(offers.getProduct().getProductId());
        if (Objects.equals(status, "accepted")) {
            offers.setStatusProcess(EStatusProcess.ACCEPTED);
            products.setIsWishlist(true);
            offerService.updateStatusOffer(products, offers, offerId);
            return new ResponseEntity<>("Status Accepted", HttpStatus.ACCEPTED);
        } else if (Objects.equals(status, "rejected")) {
            offers.setStatusProcess(EStatusProcess.REJECTED);
            products.setIsWishlist(false);
            offerService.updateStatusOffer(products, offers, offerId);
            return new ResponseEntity<>("Status Rejected", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Status not updated", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Update status offers")
    @PutMapping("update/isSold/{offerId}/{status}")
    public ResponseEntity<?> updateStatusSold(@PathVariable ("offerId") Integer offerId,
                                              @PathVariable ("status") String status) {
        Offers offers = offerService.findByOfferId(offerId);
        Products products = productService.findProductById(offers.getProduct().getProductId());
        if (Objects.equals(status, "notSold")) {
            products.setIsSold(false);
            offerService.updateStatusOffer(products, offers, offerId);
            return new ResponseEntity<>("Product status updated successfully", HttpStatus.ACCEPTED);
        } else if (Objects.equals(status, "sold")) {
            products.setIsSold(true);
            offerService.updateStatusOffer(products, offers, offerId);
            return new ResponseEntity<>("Product status updated successfully", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Product status not updated", HttpStatus.FORBIDDEN);
        }
    }
}
