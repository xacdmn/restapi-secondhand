package com.finalproject.secondhand.response;

import com.finalproject.secondhand.entity.Offers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WishlistResponse {

    Integer offerId;
    String ImageProfil;
    String fullname;
    String city;
    String Image1;
    String productName;
    String price;
    String priceNegotiated;

    public WishlistResponse() {}

    public WishlistResponse(Offers offers){
        this.offerId=offers.getOfferId();
        this.ImageProfil=offers.getUsers().getImageProfil();
        this.fullname=offers.getUsers().getFullname();
        this.city=offers.getUsers().getCity();
        this.Image1=offers.getProduct().getImage1();
        this.productName=offers.getProduct().getProductName();
        this.price=offers.getProduct().getPrice();
        this.priceNegotiated=offers.getPriceNegotiated();
    }

}
