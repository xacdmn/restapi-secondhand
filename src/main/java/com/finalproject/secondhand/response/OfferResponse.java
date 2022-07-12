package com.finalproject.secondhand.response;

import com.finalproject.secondhand.entity.Offers;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OfferResponse {

    Integer offerId;
    String ImageProfil;
    String fullname;
    String city;
    String Image1;
    String productName;
    String price;
    String priceNegotiated;
    String statusProcess;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public OfferResponse() {}

    public OfferResponse(Offers offers){
        this.offerId=offers.getOfferId();
        this.ImageProfil=offers.getUsers().getImageProfil();
        this.fullname=offers.getUsers().getFullname();
        this.city=offers.getUsers().getCity();
        this.Image1=offers.getProduct().getImage1();
        this.productName=offers.getProduct().getProductName();
        this.price=offers.getProduct().getPrice();
        this.priceNegotiated=offers.getPriceNegotiated();
        this.statusProcess=offers.getStatusProcess().name();
        this.createdAt=offers.getCreatedAt();
        this.updatedAt=offers.getUpdatedAt();
    }

}
