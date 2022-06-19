package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users addUser(Users users) {
        users.setUsername(users.getUsername());
        users.setEmail(users.getEmail());
        users.setPassword(users.getPassword());
        return userRepository.save(users);
    }

    @Override
    public Users updateUserById(Users users) {
        users.getUsername();
        Users updateUser = userRepository.findById(users.getUserId()).get();
        updateUser.setUsername(users.getUsername());
        updateUser.setEmail(users.getEmail());
        updateUser.setPassword(users.getPassword());
        updateUser.setAddress(users.getAddress());
        updateUser.setPhoneNumber(users.getPhoneNumber());
        return userRepository.save(updateUser);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
