package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.image.CloudinaryStorageService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Operation(summary = "List all user")
    @GetMapping("find-all")
    public ResponseEntity<?> listUsers() {
        List<Users> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @Operation(summary = "Find user by id")
//    @GetMapping("{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") Integer userId) {
        Users users = userService.findUsersByUserId(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @Operation(summary = "Find user by username")
//    @GetMapping("{username}")
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
        Users users = userService.findUsersByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @Operation(summary = "Find user by email")
//    @GetMapping("{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        Users users = userService.findUsersByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Update user profil")
    @PutMapping(value = "update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Users> updateUsers(@Valid @ModelAttribute UserUpdateDto update, Authentication authentication) {
        String username = authentication.getName();
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(update.getImageProfil()).getData();
        Users users = new Users();
        users.setFullname(update.getFullname());
        users.setCity(update.getCity());
        users.setAddress(update.getAddress());
        users.setPhone(update.getPhone().toString());
        users.setImageProfil(uploadImage.get("url").toString());
        return new ResponseEntity<>(userService.update(users, username), HttpStatus.ACCEPTED);
    }

}