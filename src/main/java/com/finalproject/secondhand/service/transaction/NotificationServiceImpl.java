package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Notification;
import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.NotificationRepository;
import com.finalproject.secondhand.service.product.ProductService;
import com.finalproject.secondhand.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void saveNotificationOffer(String title, String info, Offers offers, Products products, String username) {
        Notification notification = new Notification();
        Users users = userService.findByUsername(username);
        notification.setTitle(title);
        notification.setInfo(info);
        notification.setProductId(products);
        notification.setUserId(users);
        notification.setOfferId(offers);
        notificationRepository.save(notification);
    }

    @Override
    public void saveNotification(String title, Products products, Integer userId) {

    }

    @Override
    public void updateIsRead(Integer id) {

    }

    @Override
    public List<Notification> getNotification(Integer userId) {
        return null;
    }
}
