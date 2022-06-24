package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    //CREATE
    void save(SignupDto signupDto);

    //READ
    List<Users> findAll();
    Optional<Users> findById(Integer userId);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    boolean existsUsername(String username);
    boolean existsEmail(String email);

    //UPDATE
    Users update(Users users);

    //DELETE
    String deleteById(Integer userId);
    boolean deleteByUsername(String username);



}
