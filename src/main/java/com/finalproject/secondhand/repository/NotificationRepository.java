package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Notification;

import com.finalproject.secondhand.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Optional<Notification> findById(Integer id);

    List<Notification> findNotificationByUserId(Users user);

    List<Notification> findByUserId(Users users);

    @Query(value = "select * from notification where user_id=?1 order by created_on DESC",
            nativeQuery = true)
    List<Notification> findNotif(Integer userId);
}