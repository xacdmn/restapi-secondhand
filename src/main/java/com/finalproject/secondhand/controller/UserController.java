package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.ApiResponse;
import com.finalproject.secondhand.dto.user.SigninDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserCreateDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.UserRepository;
import com.finalproject.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ApiResponse signup(@RequestBody SignupDto signupDto) {
        return userService.signup(signupDto);
    }

    @PostMapping("/signin")
    public ApiResponse signin(@RequestBody SigninDto signinDto) {
        return userService.signin(signinDto);
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody UserCreateDto create) {
        return userService.createUser(create);
    }

    @PutMapping("/update/{id}")
    public ApiResponse update(@RequestBody UserUpdateDto update, @Param("id") Integer id) {
        return userService.updateUser(update, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@Param("id") Integer id) {
        return userService.deleteUser(id);
    }

}