package com.finalproject.secondhand.controller;


import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-username/{username}")
    public ResponseEntity getUserByUsername(@Schema(example = "Fill in Name") @PathVariable String username){
        Users users = userService.getUserByUsername(username);

        Map<String, Object> respBody = new HashMap<>();
        respBody.put("Id User", users.getUserId());
        respBody.put("Nama Lengkap", users.getUsername());
        respBody.put("Email", users.getEmail());
        return new ResponseEntity(respBody, HttpStatus.FOUND);
    }

    @PostMapping("/add-users")
    public ResponseEntity addUser(@Schema(example = "{" +
            "\"userId\":\"1\","+
            "\"username\":\"Wahyu\","+
            "\"password\":\"passWahyu\"," +
            "\"email\":\"wahyu@email.com\"}")@RequestBody Users users){
        userService.addUser(users);
        return new ResponseEntity(users, HttpStatus.CREATED);
    }

    @PutMapping("/update-users")
    public ResponseEntity updateUser(@Schema(example = "{" +
            "\"userId\":\"1\","+
            "\"username\":\"Ivan\","+
            "\"password\":\"passIvan\"," +
            "\"email\":\"ivan@email.com\"}") @RequestBody Users users){
        userService.updateUserById(users);
        return new ResponseEntity(users, HttpStatus.ACCEPTED);
    }

}