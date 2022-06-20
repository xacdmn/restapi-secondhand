package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/user/get")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/get/{userId}")
    public ResponseEntity<Optional<Users>> getUsersById(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<Users> updateUsers(@RequestBody UserUpdateDto userUpdateDto, @PathVariable Integer userId) {
        Users users = modelMapper.map(userUpdateDto, Users.class);
        return new ResponseEntity<>(userService.updateUsers(users, userId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<String> deleteUsers(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.ACCEPTED);
    }

}