package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.config.security.JwtUtil;
import com.finalproject.secondhand.dto.auth.JwtTokenDto;
import com.finalproject.secondhand.dto.user.SigninDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.UserDetailsImpl;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class AuthenticationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

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
    public ResponseEntity<?> signup(@Valid
                                        @Schema(example = "{" +
                                                "\"username\":\"username123\"," +
                                                "\"email\":\"username@gmail.com\"," +
                                                "\"password\":\"password123\"," +
                                                "\"role\":[\"ALL\"]" +
                                                "}")
                                        @RequestBody SignupDto signup) {
        HashMap<String, String> response = new HashMap();
        if (userService.existsUsername(signup.getUsername())) {
            response.put(signup.getUsername(), "Error: Username already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (userService.existsEmail(signup.getEmail())) {
            response.put(signup.getUsername(), "Error: Email already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        LOGGER.info(signup.toString());
        signup.setPassword(passwordEncoder.encode(signup.getPassword()));
        userService.save(signup);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
    }

    @Operation(summary = "Login user")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(
            @Schema (example = "{" +
                    "\"username\":\"username123\"," +
                    "\"password\":\"password123\"" +
                    "}")
            @RequestBody SigninDto signin) {
        LOGGER.info("logging in");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getUsername(), signin.getPassword()));
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("Username or password incorrect", HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateAccessToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LOGGER.info("User " + userDetails.getUsername() + " logged in.");
        LOGGER.info(token);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtTokenDto(token,
                userDetails.getUserId(), userDetails.getUsername(), userDetails.getEmail(),
                roles));
    }
}
