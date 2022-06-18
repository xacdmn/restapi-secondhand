package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    public Users findByUserId(Integer userId);
    public Users findByUsername(String username);
    public Users findByEmail(String email);

}

