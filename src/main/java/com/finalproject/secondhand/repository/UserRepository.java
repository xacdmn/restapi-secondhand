package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Integer> {

    List<Users> findAllByOrderByUserId();

    // Optional, untuk cari satu value
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users SET fullname= :fullname, city= :city, address= :address, phone= :phone, image_profil= :image_profil where username= :username")
    void update(
            @Param("fullname") String fullname,
            @Param("city") String city,
            @Param("address") String address,
            @Param("phone") String phone,
            @Param("image_profil") String imageProfil,
            @Param("username") String username
    );


}

