package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Users getUserByUsername(String username);

    Users addUser(Users users);

    Users updateUsersbyEmail(Users users);

}
