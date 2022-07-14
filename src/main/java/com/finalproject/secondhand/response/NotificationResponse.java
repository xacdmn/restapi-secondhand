package com.finalproject.secondhand.response;

import com.finalproject.secondhand.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationResponse {

    Integer id;
    Integer offerId;
    Integer productId;
    Integer userId;
    String title;
    String info;
    String productName;
    String price;
    String priceNegotiated;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public NotificationResponse(Notification notification){
        this.id=notification.getId();
        this.productId=notification.getProductId().getProductId();
        this.userId=notification.getUserId().getUserId();
        this.title=notification.getTitle();
        this.info=notification.getInfo();
        this.productName=notification.getProductId().getProductName();
        this.price=notification.getProductId().getPrice();
        if (notification.getOfferId() != null) {
            this.offerId = notification.getOfferId().getOfferId();
            this.priceNegotiated=notification.getOfferId().getPriceNegotiated();
        }
        this.createdAt=notification.getCreatedAt();
        this.updatedAt=notification.getUpdatedAt();
    }
}
