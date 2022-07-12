//package com.finalproject.secondhand.service.transaction;
//
//import com.finalproject.secondhand.entity.Notification;
//import com.finalproject.secondhand.entity.Offers;
//import com.finalproject.secondhand.entity.Products;
//import com.finalproject.secondhand.entity.Users;
//import com.finalproject.secondhand.repository.NotificationRepository;
//import com.finalproject.secondhand.service.product.ProductService;
//import com.finalproject.secondhand.service.user.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class NotificationServiceImpl implements NotificationService{
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private OfferService offerService;
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @Override
//    public void saveNotificationOffer(String title, String info, Offers offers, Products products, Users users) {
//        Notification notification = new Notification();
//        notification.setTitle(title);
//        notification.setInfo(info);
//        notification.setOfferId(offers);
//        notification.setProductId(products);
//        notification.setUserId(users);
//        notificationRepository.save(notification);
//    }
//
//    @Override
//    public void saveNotification(String title, Products products, Integer userId) {
//        Notification notification = new Notification();
//        notification.setTitle();
//    }
//
//    @Override
//    public void updateIsRead(Integer id) {
//
//    }
//
//    @Override
//    public List<Notification> getNotification(Integer userId) {
//        return null;
//    }
//}
