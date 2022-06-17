package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.UserCreateDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController @RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody UserCreateDto create) {
        Users users = modelMapper.map(create, Users.class);
        return new ResponseEntity<>(userService.addUser(users), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Users>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody UserUpdateDto update, @PathVariable Integer id) {
        Users users = modelMapper.map(update, Users.class);
        return new ResponseEntity<>(userService.updateUser(users, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.ACCEPTED);
    }

}