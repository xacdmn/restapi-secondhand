package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.config.security.JwtUtil;
import com.finalproject.secondhand.dto.auth.JwtTokenDto;
import com.finalproject.secondhand.dto.user.SigninUsernameDto;
import com.finalproject.secondhand.dto.user.SigninEmailDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.PasswordResetToken;
import com.finalproject.secondhand.entity.UserDetailsImpl;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.PasswordTokenRepository;
import com.finalproject.secondhand.service.user.EmailService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.finalproject.secondhand.config.utils.SiteUrl.getSiteURL;

@RestController
@Tag(name = "Authentication", description = "API for Login and Register")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000", "*"}, allowedHeaders = "*")
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

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Operation(summary = "NOT FOR PUBLIC")
    @GetMapping("/list-user")
    public List<Users> listAll() {
        return userService.findAll();
    }

    @Operation(summary = "Registers a new user")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Valid
            @Schema(example = "{" +
                    "\"fullname\":\"Ellda Artha Airlannga\"," +
                    "\"username\":\"ellda\"," +
                    "\"email\":\"ellda@gmail.com\"," +
                    "\"password\":\"ellda123\"" +
                    "}")
            @RequestBody SignupDto signup) {
        HashMap<String, String> response = new HashMap();
        if (userService.existsUsername(signup.getUsername())) {
            response.put("error", "Username already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (userService.existsEmail(signup.getEmail())) {
            response.put("error", "Email already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        LOGGER.info(signup.toString());
        signup.setPassword(passwordEncoder.encode(signup.getPassword()));
        signup.setFullname(signup.getFullname());
        LOGGER.info(signup.getFullname());
        userService.save(signup);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
    }

    @Operation(summary = "Login user with username or email")
    @PostMapping("/signin/email")
    public ResponseEntity<?> signin(
            @Schema(example = "{" +
                    "\"email\":\"ellda\"," +
                    "\"password\":\"ellda123\"" +
                    "}")
            @RequestBody SigninEmailDto signin) {
        LOGGER.info("logging in");
        HashMap<String, String> response = new HashMap();
        if (userService.existsUsername(signin.getEmail())) {
            if (!passwordEncoder.matches(signin.getPassword(), userService.findByUsername(signin.getEmail()).getPassword())) {
                response.put("error", "Password incorrect");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userService.loginUsername(signin), HttpStatus.OK);
        } else if (userService.existsEmail(signin.getEmail())){
            Users users = userService.findUserByEmail(signin.getEmail());
            if (!passwordEncoder.matches(signin.getPassword(), userService.findByUsername(users.getUsername()).getPassword())) {
                response.put("error", "Password incorrect");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userService.loginEmail(signin), HttpStatus.OK);
        } else {
            response.put("error", "user not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

//    @Operation(summary = "Login user with email")
//    @PostMapping("/signin/email")
//    public ResponseEntity<?> signinEmail(
//            @Schema(example = "{" +
//                    "\"email\":\"ellda@gmail.com\"," +
//                    "\"password\":\"ellda123\"" +
//                    "}")
//            @RequestBody SigninEmailDto signin) {
//        LOGGER.info("logging in");
//        HashMap<String, String> response = new HashMap();
//        if (!userService.existsEmail(signin.getEmail())){
//            response.put("error", "User not found");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//        Users users = userService.findUserByEmail(signin.getEmail());
//        if (!passwordEncoder.matches(signin.getPassword(), userService.findByUsername(users.getUsername()).getPassword())) {
//            response.put("error", "Password incorrect");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),
//                signin.getPassword()));
//        if (!authentication.isAuthenticated()) {
//            return new ResponseEntity<>("Email or password incorrect", HttpStatus.OK);
//        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtUtil.generateAccessToken(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        LOGGER.info("User " + userDetails.getUsername() + " logged in.");
//        LOGGER.info(token);
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(new JwtTokenDto(token,
//                userDetails.getUserId(), userDetails.getUsername(), userDetails.getEmail(),
//                roles));
//    }

//    @Operation(summary = "reset password")
//    @PostMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@RequestParam String email,
//                                           HttpServletRequest request) {
//        Users users = userService.findUserByEmail(email);
//        HashMap<String, String> response = new HashMap();
//        if (users == null) {
//            response.put("error", "user not found");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//        String token = UUID.randomUUID().toString();
//        try {
//            userService.createPasswordResetToken(users, token);
//            String resetPasswordLink = getSiteURL(request) + "/reset-password?token=" + token;
//
//            emailService.sendMail(email, resetPasswordLink);
//
//            response.put("email", email);
//            response.put("token", token);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            response.put("error", "email not found!");
//        }
//        return new ResponseEntity<>("Back to form reset password", HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("change-reset-password")
//    public ResponseEntity<?> changeResetPassword(@RequestBody @Valid Map<String, Object> newPassword,
//                                                 @RequestBody @Valid Map<String, Object> confirmPassword,
//                                                 @RequestParam ("token") String token) {
//
//        String result = emailService.validatePasswordResetToken(token);
//        HashMap<String, String> response = new HashMap();
//        if(result == null) {
//            response.put("error", "Token is invalid or Expired");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//
//        PasswordResetToken passwordResetToken = passwordTokenRepository.findByToken(token);
//
//        String username = passwordResetToken.getUsers().getUsername();
//        Users users = userService.findByUsername(username);
//        if(users != null) {
//            if (newPassword.equals(confirmPassword)) {
//                userService.updateResetPassword(users, newPassword.get("new").toString());
//                return new ResponseEntity<>("Password has been change", HttpStatus.ACCEPTED);
//            } else {
//                response.put("error", "new password and confirm password not same");
//                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            response.put("error", "user not found");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }
}