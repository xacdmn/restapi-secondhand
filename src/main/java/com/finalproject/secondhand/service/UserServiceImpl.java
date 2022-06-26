package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.repository.RoleRepository;
import com.finalproject.secondhand.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Autowired
    private RoleRepository roleRepository;

    public void save(SignupDto signupDto) {
        Users users = new Users(signupDto);
//        Set<String> strRoles = signupDto.getRoles();
//        Set<Roles> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Roles role = roleRepository.findByRole(ERole.BUYER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
//            roles.add(role);
//        } else {
//            strRoles.forEach(role -> {
//                Roles mRole = roleRepository.findByRole(ERole.valueOf(role))
//                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found"));
//                roles.add(mRole);
//            });
//        }
//        users.setRoles(roles);
        users.setPassword(signupDto.getPassword());
        userRepository.save(users);
    }

    public List<UserDto> findAll() {
        return userRepository.findAllByOrderByUserId().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public Optional<UserDto> findById(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            return Optional.of(new UserDto(userRepository.findById(userId).get()));
        }
        return Optional.empty();
    }

    public Optional<UserDto> findByUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return Optional.of(new UserDto(userRepository.findByUsername(username).get()));
        }
        return Optional.empty();
    }

    public Optional<UserDto> findByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return Optional.of(new UserDto(userRepository.findByEmail(email).get()));
        }
        return Optional.empty();
    }

    public boolean existsUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public boolean existsEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public void update(UserUpdateDto update, String username) {
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(update.getImageProfil()).getData();
        String fullname = update.getFullname();
        String city = update.getCity();
        String address = update.getAddress();
        String phone = update.getPhone();
        String imageProfil = uploadImage.get("url").toString();
        userRepository.update(fullname, city, address, phone, imageProfil, username);
    }


    @Override
    public String deleteById(Integer userId) {
        userRepository.deleteById(userId);
        return "Success delete user";
    }

    public boolean deleteByUsername(String username) {
        Optional<Users> users = userRepository.findByUsername(username);
        if (users.isPresent()) {
            userRepository.deleteById(users.get().getUserId());
            return true;
        }
        return false;
    }

}