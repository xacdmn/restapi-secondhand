package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<Users> postUsers(@RequestBody SignupDto signupDto) {
        Users users = modelMapper.map(signupDto, Users.class);
        return new ResponseEntity<>(userService.postUsers(users), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Optional<Users>> getUsersById(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Users> updateUsers(@RequestParam ("image_profil") MultipartFile imageProfil,
                                             @RequestParam ("fullname") String fullName,
                                             @RequestParam ("city") String city,
                                             @RequestParam ("address") String address,
                                             @RequestParam ("phone_number") Long phoneNumber,
                                             @PathVariable Integer userId) {
        return new ResponseEntity<>(userService.updateUsers(imageProfil, fullName, city, address, phoneNumber, userId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUsers(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.ACCEPTED);
    }

}