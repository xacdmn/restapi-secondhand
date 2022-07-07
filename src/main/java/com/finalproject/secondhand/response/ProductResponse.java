package com.finalproject.secondhand.response;

import com.finalproject.secondhand.entity.Products;
import lombok.*;

@Data
public class ProductResponse {

    Integer productId;
    String image1;
    String image2;
    String image3;
    String image4;
    String productName;
    String category;
    String price;
    String description;
    String fullname;
    String city;
    boolean isPublished;
    boolean isSold;

    public ProductResponse() {}

    public ProductResponse(Products products) {
        this.productId=products.getProductId();
        this.image1=products.getImage1();
        this.image2=products.getImage2();
        this.image3=products.getImage3();
        this.image4=products.getImage4();
        this.productName=products.getProductName();
        this.category=products.getCategory();
        this.price=products.getPrice();
        this.description=products.getDescription();
        this.fullname=products.getUsers().getFullname();
        this.city=products.getUsers().getCity();
        this.isPublished=products.getIsPublished();
        this.isSold=products.getIsSold();
    }
}
