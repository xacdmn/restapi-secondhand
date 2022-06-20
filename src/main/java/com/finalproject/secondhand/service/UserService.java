package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    List<Users> getAllUsers();
    Optional<Users> getUserById(Integer userId);
    Users updateUsers(Users body, Integer userId);
    String deleteUser(Integer userId);


}
