package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.UserDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;



    @Operation(summary = "List user")
    @GetMapping("users")
    public ResponseEntity<?> listUsers() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Find user by id")
    @GetMapping("{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") Integer userId) {
        Optional<UserDto> users = userService.findById(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Find user by username")
    @GetMapping("{username}")
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
        Optional<UserDto> users = userService.findByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Find user by email")
    @GetMapping("{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        Optional<UserDto> users = userService.findByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Update user profil")
    @PutMapping(value = "update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUsers(@ModelAttribute @Valid UserUpdateDto update, Authentication authentication) {
        String username = authentication.getName();
        userService.update(update, username);
        return new ResponseEntity<>("Update user successfull", HttpStatus.ACCEPTED);
    }

}