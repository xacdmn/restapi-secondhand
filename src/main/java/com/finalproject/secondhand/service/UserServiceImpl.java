package com.finalproject.secondhand.service;

import com.finalproject.secondhand.config.MessageString;
import com.finalproject.secondhand.dto.ApiResponse;
import com.finalproject.secondhand.dto.user.SigninDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserCreateDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.ResponseStatus;
import com.finalproject.secondhand.execption.AuthenticationFailException;
import com.finalproject.secondhand.execption.CustomExecption;
import com.finalproject.secondhand.mapper.MapperUser;
import com.finalproject.secondhand.repository.UserRepository;
import com.finalproject.secondhand.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MapperUser mapperUser;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    public ApiResponse signup(SignupDto signupDto)  throws CustomExecption {
        // Check to see if the current username and email address has already been registered.
        if (Helper.notNull(userRepository.findByUsername(signupDto.getUsername()))) {
            // If the username has been registered then throw an exception.
            throw new CustomExecption("Username already exists");
        } else if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // If the email address has been registered then throw an exception.
            throw new CustomExecption("Email already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        Users users = new Users(signupDto.getUsername(), signupDto.getEmail(), encryptedPassword );
        // save the User
        userRepository.save(users);
        // generate token for user-
        // success in creating
        return new ApiResponse(ResponseStatus.SUCCESS.toString(), MessageString.USER_CREATED);
    }

    public ApiResponse signin(SigninDto signinDto) throws CustomExecption {
        // first find User by email
        Users users = userRepository.findByEmail(signinDto.getEmail());
        if(!Helper.notNull(users)){
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!users.getPassword().equals(hashPassword(signinDto.getPassword()))){
                // passowrd doesnot match
                throw  new AuthenticationFailException(MessageString.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomExecption(e.getMessage());
        }

        return new ApiResponse (ResponseStatus.SUCCESS.toString(), "Login success");
    }

    public ApiResponse createUser(UserCreateDto create) {
        userRepository.save(mapperUser.mapCreate(create));
        return new ApiResponse(ResponseStatus.SUCCESS.toString(), MessageString.USER_CREATED);
    }

    public ApiResponse updateUser(UserUpdateDto update, Integer id) {
        userRepository.findById(id);
        userRepository.save(mapperUser.mapUpdate(update));
        return new ApiResponse(ResponseStatus.SUCCESS.toString(), MessageString.USER_UPDATE);
    }

    public ApiResponse deleteUser(Integer id) {
        userRepository.deleteById(id);
        return new ApiResponse(ResponseStatus.SUCCESS.toString(), MessageString.USER_DELETE);
    }

}
