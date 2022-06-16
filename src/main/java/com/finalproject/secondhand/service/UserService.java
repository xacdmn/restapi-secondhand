package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.ApiResponse;
import com.finalproject.secondhand.dto.user.SigninDto;
import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserCreateDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public ApiResponse signup(SignupDto signupDto);
    public ApiResponse signin(SigninDto signinDto);
    public ApiResponse createUser(UserCreateDto create);
    public ApiResponse updateUser(UserUpdateDto update, Integer id);
    public ApiResponse deleteUser(Integer id);

}
