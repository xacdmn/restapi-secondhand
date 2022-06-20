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
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Users updateUsers(Users body, Integer userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User tidak ditemukan!"));
        users.setFullname(body.getFullname());
        users.setCity(body.getCity());
        users.setAddress(body.getAddress());
        users.setPhoneNumber(body.getPhoneNumber());
        return userRepository.save(users);
    }

    @Override
    public String deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return "User telah dihapus";
    }
}
