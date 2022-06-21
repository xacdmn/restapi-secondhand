package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.CloudinaryStorageService;
import com.finalproject.secondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final CloudinaryStorageService cloudinaryStorageService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<Users> postUsers(@RequestBody SignupDto signupDto) {
        Users users = modelMapper.map(signupDto, Users.class);
        return new ResponseEntity<>(userService.addUsers(users), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/current")
    public String currentUser(Authentication authentication){
        return authentication.getName();
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Optional<Users>> getUsersById(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Users> updateUsers(UserUpdateDto userUpdateDto, Authentication authentication) {
        String username = authentication.getName();
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(userUpdateDto.getImageProfil()).getData();
        Users users = new Users();
        users.setFullname(userUpdateDto.getFullname());
        users.setCity(userUpdateDto.getCity());
        users.setAddress(userUpdateDto.getAddress());
        users.setPhone(userUpdateDto.getPhone());
        users.setImageProfil(uploadImage.get("url").toString());
        return new ResponseEntity<>(userService.updateUsers(users, username), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUsers(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.ACCEPTED);
    }

}