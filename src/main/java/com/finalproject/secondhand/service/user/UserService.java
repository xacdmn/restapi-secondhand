package com.finalproject.secondhand.service.user;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    //CREATE
    void save(SignupDto signupDto);

    //READ
    List<Users> findAll();
    Users findByUserId(Integer userId);
    Users findByEmail(String email);

    boolean existsUsername(String username);
    boolean existsEmail(String email);

    //UPDATE
    Users update(Users body, String username);
    String changePassword(Users body, String username);

    //DELETE
    String deleteById(Integer userId);

    //AUTH
    Users findByUsername(String username);

}
