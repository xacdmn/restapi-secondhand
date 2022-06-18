package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.Enum.Role;
import com.finalproject.secondhand.config.JwtUtils;
import com.finalproject.secondhand.entity.*;
import com.finalproject.secondhand.repository.RoleRepository;
import com.finalproject.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;
    public AuthController(){
    }

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          JwtUtils jwtUtils, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/Signin")
    public ResponseEntity<?> authenticateUser (@Valid @RequestBody Map<String, Object> login){
        Users users = userRepository.findByEmail(login.get("email").toString());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getUsername(), login.get("password"))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles));
    }

    @PostMapping("/Signup")
    public ResponseEntity<MessageResponse> registerUser (@Valid @RequestBody SignupRequest signupRequest){
        boolean emailExist = userRepository.existsByEmail(signupRequest.getEmail());
        if(Boolean.TRUE.equals(emailExist)){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error : Email is already taken"));
        }

        Users users = new Users(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Set<Roles> roles = new HashSet<>();
        if (strRoles == null){
            Roles role = roleRepository.findByName(Role.SELLER)
                    .orElseThrow(() -> new RuntimeException("Error : role is not found"));
            roles.add(role);
        }else{
            strRoles.forEach(role -> {
                Roles roles1 = roleRepository.findByName(Role.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error : Role " + role + "is not found"));
                roles.add(roles1);
            });
        }
        users.setRoles(roles);
        userRepository.save(users);
        return ResponseEntity.ok(new MessageResponse("User Registered Succesfully"));
    }

}
