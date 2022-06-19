package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Users getUserByUsername(String username);

    Users getUserByEmail(String email);

    Users addUser(Users users);

    Users updateUserById(Users users);

    List<Users> getAllUsers();

}
