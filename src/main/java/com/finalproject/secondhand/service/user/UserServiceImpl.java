package com.finalproject.secondhand.service.user;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.repository.RoleRepository;
import com.finalproject.secondhand.repository.UserRepository;
import com.finalproject.secondhand.service.user.UserService;
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
        Set<String> strRoles = signupDto.getRoles();
        Set<Roles> roles = new HashSet<>();

        if(strRoles == null) {
            Roles role = roleRepository.findByRole(ERole.ALL)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                Roles roles1 = roleRepository.findByRole(ERole.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found"));
                roles.add(roles1);
            });
        }
        users.setRoles(roles);
        users.setPassword(users.getPassword());
        userRepository.save(users);
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