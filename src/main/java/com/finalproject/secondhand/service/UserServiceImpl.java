package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users addUser(Users users) {
        users.setUsername(users.getUsername());
        users.setEmail(users.getEmail());
        users.setPassword(users.getPassword());
        return userRepository.save(users);
    }

    @Override
    public Users updateUsersbyEmail(Users users) {
        Users updateUser = userRepository.findByEmail(users.getEmail());
        updateUser.setUsername(users.getUsername());
        updateUser.setAddress(users.getAddress());
        updateUser.setPhoneNumber(users.getPhoneNumber());
        return userRepository.save(users);
    }
}
