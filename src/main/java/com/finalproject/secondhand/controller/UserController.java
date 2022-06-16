package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-userByUsername/{username}")
    public ResponseEntity<RestTemplate> getUserByUsername(@Schema(example = "Masukan username yang sudah dibuat") @PathVariable String username){
        userService.getUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PostMapping("/add-users")
    public ResponseEntity<RestTemplate> addUser (@RequestBody Users users){
        userService.addUser(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-users")
    public ResponseEntity<RestTemplate> updateUser(@RequestBody Users users){
        userService.updateUsersbyEmail(users);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}