package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Users findByUserId(Integer userId);
    Users findByUsername(String username);
    Users findByEmail(String email);

}

