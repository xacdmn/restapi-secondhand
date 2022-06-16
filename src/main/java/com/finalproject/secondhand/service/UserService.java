package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    List<Users> getAllUser();
    Optional<Users> getUserById(Integer id);
    Users updateUser(Users body, Integer id);
    String deleteUser(Integer id);

}
