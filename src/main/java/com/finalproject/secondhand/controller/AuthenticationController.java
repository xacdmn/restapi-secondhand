package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.config.JwtUtil;
import com.finalproject.secondhand.dto.user.JwtTokenDto;
import com.finalproject.secondhand.dto.user.SigninDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Registers a new user")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(SignupDto signupDto) {
        Map<String, String> response = new HashMap<>();
        if (userService.existsUsername(signupDto.getUsername())) {
            response.put(signupDto.getUsername(), "Error: Username already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (userService.existsEmail(signupDto.getEmail())) {
            response.put(signupDto.getUsername(), "Error: Email already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        logger.info(signupDto.toString());
        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        userService.save(signupDto);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
    }

    @Operation(summary = "Login user")
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SigninDto signinDto) {
        logger.info("logging in");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("Username or password incorrect", HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateAccessToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("User " + userDetails.getUsername() + " logged in.");
        logger.info(token);
        Optional<Users> user = userService.findByUsername(userDetails.getUsername());

        if (!user.isPresent()) {
            return new ResponseEntity<>("User or password incorrect", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(new JwtTokenDto(token, user.get()));
    }


}
