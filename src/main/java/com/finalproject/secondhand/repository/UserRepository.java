package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findUsersByEmail(String email);
    Users findUserById(Integer id);
    Users findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}

