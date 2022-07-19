package com.finalproject.secondhand.service.user;

import com.finalproject.secondhand.config.security.JwtUtil;
import com.finalproject.secondhand.dto.auth.JwtTokenDto;
import com.finalproject.secondhand.dto.user.SigninEmailDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.entity.PasswordResetToken;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.UserDetailsImpl;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.PasswordTokenRepository;
import com.finalproject.secondhand.repository.RoleRepository;
import com.finalproject.secondhand.repository.UserRepository;
import com.finalproject.secondhand.dto.response.UserDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void save(SignupDto signupDto) {
        Users users = new Users(signupDto);
        addRoleToUsers(users, users.getRoles());
        users.setFullname(users.getFullname());
        users.setPassword(users.getPassword());
        userRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }

    @Override
    public UserDetailResponse curentUserDetail(String username) {
        Users users = userRepository.findUsersByUsername(username);
        return new UserDetailResponse(users);
    }

    @Override
    public JwtTokenDto loginEmail(SigninEmailDto signin) {
        Users users = userRepository.findUsersByEmail(signin.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),
                signin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateAccessToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LOGGER.info("User " + userDetails.getUsername() + " logged in.");
        LOGGER.info(token);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtTokenDto(token,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public JwtTokenDto loginUsername(SigninEmailDto signin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getEmail(),
                signin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateAccessToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LOGGER.info("User " + userDetails.getUsername() + " logged in.");
        LOGGER.info(token);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtTokenDto(token,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    private void addRoleToUsers(Users users, Collection<Roles> request) {
        List<Roles> roles = new ArrayList<>();
        if (request == null) {
            List<Roles> allRoles = roleRepository.findAll();
            roles.addAll(allRoles);
        }
        users.setRoles(roles);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findUsersByUsername(username);
    }

    @Override
    public void createPasswordResetToken(Users users, String token) {
        PasswordResetToken myToken = new PasswordResetToken(users, token);
        passwordTokenRepository.save(myToken);
    }


    @Override
    public void updateResetPassword(Users body, String newPassword) {
        Users users = findByUsername(body.getUsername());
        users.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(users);
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Users update(Users body, String username) {
        Users users = userRepository.findByUsername(username);
        if (body.getFullname() != null) {
            if (body.getFullname().length() > 0) {
                users.setFullname(body.getFullname());
            }
        }
        if (body.getCity() != null) {
            if (body.getCity().length() > 0) {
                users.setCity(body.getCity());
            }
        }
        if (body.getAddress() != null) {
            if (body.getAddress().length() > 0) {
                users.setAddress(body.getAddress());
            }
        }
        if (body.getPhone() != null) {
            if (body.getPhone().length() > 0) {
                users.setPhone(body.getPhone());
            }
        }
        if (body.getImageProfil() != null) {
            users.setImageProfil(body.getImageProfil());
        }
        return userRepository.save(users);
    }

    @Override
    public String changePassword(Users body, String username) {
        Users users = userRepository.findByUsername(username);
        users.setPassword(body.getPassword());
        userRepository.save(users);
        return "Password has been change";
    }

    @Override
    public String deleteById(Integer userId) {
        userRepository.deleteById(userId);
        return "Account Deleted";
    }


}