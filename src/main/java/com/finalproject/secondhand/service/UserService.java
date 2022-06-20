package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    Users postUsers(Users body);
    List<Users> getAllUsers();
    Optional<Users> getUserById(Integer userId);
    Users updateUsers(MultipartFile file, String fullName, String city, String address, Long phoneNumber, Integer userId);
    String deleteUser(Integer userId);


}
