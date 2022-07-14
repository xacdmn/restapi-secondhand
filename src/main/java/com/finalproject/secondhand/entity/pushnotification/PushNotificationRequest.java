package com.finalproject.secondhand.entity.pushnotification;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PushNotificationRequest {

    private String title;
    private String info;
    private Offers offers;
    private Products products;
    private Users users;
    private String token;

}
