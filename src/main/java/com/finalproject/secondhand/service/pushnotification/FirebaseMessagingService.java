package com.finalproject.secondhand.service.pushnotification;

import com.finalproject.secondhand.dto.response.NotificationResponse;
import org.springframework.stereotype.Component;

@Component
public interface FirebaseMessagingService {

    void sendNotification(String title, String body, NotificationResponse response);

}
