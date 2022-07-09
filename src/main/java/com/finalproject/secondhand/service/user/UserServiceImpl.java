package com.finalproject.secondhand.service.user;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.Users;
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
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
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
        if (body.getFullname() != null) {
            if (body.getFullname().length() > 0) {
                users.setFullname(body.getFullname());
            }
        }
        if (body.getCity() != null) {
            if (body.getCity().length() > 0) {
                users.setCity(body.getCity());
            }
        }
        if (body.getAddress() != null) {
            if (body.getAddress().length() > 0) {
                users.setAddress(body.getAddress());
            }
        }
        if (body.getPhone() != null) {
            if (body.getPhone().length() > 0) {
                users.setPhone(body.getPhone());
            }
        }
        if (body.getImageProfil() != null) {
            users.setImageProfil(body.getImageProfil());
        }
        return userRepository.save(users);
    }

    @Override
    public String changePassword(Users body, String username) {
        Users users = userRepository.findByUsername(username);
        users.setPassword(body.getPassword());
        userRepository.save(users);
        return "Password has been change";
    }

    @Override
    public String deleteById(Integer userId) {
        userRepository.deleteById(userId);
        return "Account Deleted";
    }
}