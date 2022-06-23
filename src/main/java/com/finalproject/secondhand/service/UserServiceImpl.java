package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user != null){
            log.info("User found in the database : {}", username);
        }else{
            log.error("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        assert user != null;
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }

    @Override
    public Users addUsers(Users body) {
        Users usersExists = userRepository.findByUsername(body.getUsername());
        if(usersExists != null){
            throw  new IllegalArgumentException(String.format("User with username '%s' already exists", body.getUsername()));
        }
        String encodePassword = bCryptPasswordEncoder.encode(body.getPassword());
        body.setPassword(encodePassword);
        userRepository.save(body);
        return body;

    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Users updateUsers(Users body, String username) {
        Users users = userRepository.findByUsername(username);
        users.setFullname(body.getFullname());
        users.setCity(body.getCity());
        users.setAddress(body.getAddress());
        users.setPhone(body.getPhone());
        users.setImageProfil(body.getImageProfil());
        return userRepository.save(users);
    }

    @Override
    public String deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return "Success delete user";
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
