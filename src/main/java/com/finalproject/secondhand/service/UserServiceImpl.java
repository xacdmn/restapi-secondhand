package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Users;
//import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class UserServiceImpl implements UserService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    public void save(SignupDto signupDto) {
        //
        Users users = new Users(signupDto);
//        if (signupDto.getRoles().isEmpty()) {
//            users.getRoles().add(
//                    roleService.findByRole(ERole.BUYER).orElseThrow(() ->
//                            new RuntimeException("Error: No role Buyer Found"))
//            );
//        } else {
//            LOGGER.info(roleService.findAll().toString());
//            signupDto.getRoles().forEach(role -> users.getRoles().add(
//                    roleService.findByRole(getEnumIgnoreCase(ERole.class, role)).orElseThrow(() ->
//                            new RuntimeException("Error: No role '" + role + "' Found. Use `Buyer` as default."))
//            ));
//        }
        users.setPassword(signupDto.getPassword());
        userRepository.save(users);
    }

    public List<Users> findAll() {
        return userRepository.findAllByOrderByUserId();
    }

    public Optional<Users> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public boolean existsEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public Users update(Users users) {
       return userRepository.save(users);
    }


    @Override
    public String deleteById(Integer userId) {
        userRepository.deleteById(userId);
        return "Success delete user";
    }

    public boolean deleteByUsername(String username) {
        Optional<Users> users = userRepository.findByUsername(username);
        if (users.isPresent()) {
            userRepository.deleteById(users.get().getUserId());
            return true;
        }
        return false;
    }

}
