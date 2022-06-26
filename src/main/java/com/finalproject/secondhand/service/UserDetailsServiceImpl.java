package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserDto;
import com.finalproject.secondhand.entity.UserDetailsImpl;
import com.finalproject.secondhand.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    public void saveUser(SignupDto signupDto) {
        userService.save(signupDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> user = userService.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        } else {
            LOGGER.info("Found user : " + username);
            return new UserDetailsImpl(new Users(user.get().setPassword(user.get().getPassword())));
        }
    }
}
