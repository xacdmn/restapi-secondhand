package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Notification;
import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.NotificationRepository;
import com.finalproject.secondhand.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void saveNotification(String title, String info, Offers offersId, Products productsId, Users users,
                                 Boolean isRead) {
        Users user = userService.findByUsername(users.getUsername());
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setInfo(info);
        notification.setProductId(productsId);
        notification.setUserId(user);
        notification.setOfferId(offersId);
        notificationRepository.save(notification);
    }

    @Override
    public void saveNotification(String title, Products productsId, String username) {
        Users user = userService.findByUsername(username);
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setProductId(productsId);
        notification.setUserId(user);
        notificationRepository.save(notification);
    }

    @Override
    public void updateIsRead(Integer id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.ifPresent(notification1 -> {
            notification1.setIsRead(true);
            notificationRepository.save(notification1);
        });
    }

    @Override
    public List<Notification> getNotification(Integer userId) {
        return notificationRepository.findNotif(userId);
    }

}
