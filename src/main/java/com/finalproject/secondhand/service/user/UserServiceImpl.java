package com.finalproject.secondhand.service.user;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.repository.RoleRepository;
import com.finalproject.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void save(SignupDto signupDto) {
        Users users = new Users(signupDto);
        addRoleToUsers(users, users.getRoles());
        users.setPassword(users.getPassword());
        userRepository.save(users);
    }

    private void addRoleToUsers(Users users, Collection<Roles> request) {
        List<Roles> roles = new ArrayList<>();
        if (request == null) {
            List<Roles> allRoles = roleRepository.findAll();
            roles.addAll(allRoles);
        }
        users.setRoles(roles);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Integer userId) {
        return userRepository.findUsersByUserId(userId);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Users update(Users body, String username) {
        Users users = userRepository.findByUsername(username);
        users.setFullname(body.getFullname());
        users.setCity(body.getCity());
        users.setAddress(body.getAddress());
        users.setPhone(body.getPhone());
        users.setImageProfil(body.getImageProfil());
        return userRepository.save(users);
    }

    @Override
    public String deleteById(Integer userId) {
        userRepository.deleteById(userId);
        return "Success delete user";
    }
}