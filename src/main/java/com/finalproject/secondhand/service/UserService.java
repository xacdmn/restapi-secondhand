package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserDto;
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
    List<UserDto> findAll();
    Optional<UserDto> findById(Integer userId);
    Optional<UserDto> findByUsername(String username);
    Optional<UserDto> findByEmail(String email);
    boolean existsUsername(String username);
    boolean existsEmail(String email);

    //UPDATE
    void update(UserUpdateDto update, String username);

    //DELETE
    String deleteById(Integer userId);
    boolean deleteByUsername(String username);



}
