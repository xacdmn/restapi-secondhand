package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Notification;
<<<<<<< HEAD
import com.finalproject.secondhand.entity.Users;
=======
>>>>>>> 57ccd54 (Create Notification)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Optional<Notification> findById(Integer id);

<<<<<<< HEAD
    List<Notification> findByUserId(Users users);

=======
>>>>>>> 57ccd54 (Create Notification)
    @Query(value ="select * from notification where user_id=?1 order by created_on DESC" ,
            nativeQuery = true)
    List<Notification> findNotif(Integer userId);

}
