package com.finalproject.secondhand.service.pushnotification;

import com.finalproject.secondhand.dto.response.NotificationResponse;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService{

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseMessagingService.class);

    private final FirebaseMessaging firebaseMessaging;


    public FirebaseMessagingServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    private String getRandomToken() {
        return UUID.randomUUID().toString();
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    @Override
    public void sendNotification(String title, String info, NotificationResponse response) {

        final Map<String, String> data = new HashMap<>();
        data.put("id", response.getId().toString());
        data.put("offerId", response.getOfferId().toString());
        data.put("userId", response.getUserId().toString());
        data.put("productName", response.getProductName());
        data.put("price", response.getPrice());
        data.put("priceNegotiated", response.getPriceNegotiated());
        data.put("createdAt", response.getCreatedAt().toString());
        data.put("updatedAt", response.getUpdatedAt().toString());

        String token = getRandomToken();

        Notification notification = Notification
                .builder()
                .setImage(response.getImage1())
                .setTitle(title)
                .setBody(info)
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(data)
                .build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        try {
            String responseJson = sendAndGetResponse(message);
            LOGGER.info("Sent message to token. Device token: " + token + ", " + responseJson + " msg "+ jsonOutput);
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
