package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Roles;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        Users users = userRepository.findByUsername(username);
        if(users != null){
            log.info("User found in the database : {}", username);
        }else{
            log.error("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        assert users != null;
        for (Roles roles : users.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
    }

    @Override
    public Users postUsers(Users body) {
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
    public Users updateUsers(MultipartFile file, String fullName, String city, String address, Long phoneNumber, Integer userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User tidak ditemukan!"));
        try {
            users.setImageProfil(Base64.getEncoder().encodeToString(file.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
        users.setFullname(fullName);
        users.setCity(city);
        users.setAddress(address);
        users.setPhoneNumber(phoneNumber);
        return userRepository.save(users);
    }

    @Override
    public String deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return "User telah dihapus";
    }
}
