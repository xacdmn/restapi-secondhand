package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.dto.user.ChangePasswordDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.image.CloudinaryStorageService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Operation(summary = "List all user")
    @GetMapping("find-all")
    public ResponseEntity<?> listUsers() {
        List<Users> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Find user by id")
    @GetMapping("{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(userService.findByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Find user by username")
    @GetMapping("{username}")
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Find user by email")
    @GetMapping("{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @Operation(summary = "Update user profil")
    @PutMapping(value = "update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Users> updateUsers(@Valid @ModelAttribute UserUpdateDto update, @RequestParam (required = false) MultipartFile imageProfil
            , Authentication authentication) {
        String username = authentication.getName();
        Users users = new Users();
        users.setFullname(update.getFullname());
        users.setCity(update.getCity());
        users.setAddress(update.getAddress());
        users.setPhone(update.getPhone());
        LOGGER.info(String.valueOf(imageProfil));
        if (imageProfil != null) {
            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(imageProfil).getData();
            users.setImageProfil(uploadImage.get("url").toString());
        }
        return new ResponseEntity<>(userService.update(users, username), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Change Password")
    @GetMapping( "change-password")
    public ResponseEntity<?> changePassword(
            @Schema(example = "{" +
                    "\"oldPassword\":\"ellda123\"," +
                    "\"newPassword\":\"baru123\"," +
                    "\"confirmPassword\":\"baru123\"" +
                    "}")
            @ModelAttribute ChangePasswordDto change, Authentication valid) {
        String username = valid.getName();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, change.getOldPassword()));
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("Old password incorrect", HttpStatus.FORBIDDEN);
        }
        Users users = new Users();
        if (change.getNewPassword().equals(change.getNewPassword())) {
            users.setPassword(passwordEncoder.encode(change.getNewPassword()));
            return new ResponseEntity<>(userService.changePassword(users, username), HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("New password and confirm password not same", HttpStatus.FORBIDDEN);
        }
    }
}