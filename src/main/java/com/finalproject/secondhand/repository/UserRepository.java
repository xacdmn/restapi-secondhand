package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
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

}

