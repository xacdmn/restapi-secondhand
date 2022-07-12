package com.finalproject.secondhand.response;

import com.finalproject.secondhand.entity.Products;
import lombok.Data;

@Data
public class HistoryProductResponse {

    Integer productId;
    String image1;
    String productName;
    Integer categoryId;
    String price;
    String description;
    String imageProfil;
    String fullname;
    String city;
    boolean isSold;

    public HistoryProductResponse() {}

    public HistoryProductResponse(Products products) {
        this.productId=products.getProductId();
        this.image1=products.getImage1();
        this.productName=products.getProductName();
        this.categoryId=products.getCategories().getId();
        this.price=products.getPrice();
        this.description=products.getDescription();
        this.imageProfil=products.getUsers().getImageProfil();
        this.fullname=products.getUsers().getFullname();
        this.city=products.getUsers().getCity();
        this.isSold=products.getIsSold();
    }

}
