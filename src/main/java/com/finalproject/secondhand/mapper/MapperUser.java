package com.finalproject.secondhand.mapper;

import com.finalproject.secondhand.dto.user.UserCreateDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class MapperUser {

    public Users mapUpdate(UserUpdateDto update) {
        Users users = new Users();
        users.setEmail(update.getEmail());
        users.setPassword(update.getPassword());
        users.setAddress(update.getAddress());
        users.setPhoneNumber(update.getPhoneNumber());
        return users;
    }

    public Users mapCreate(UserCreateDto create) {
        Users users = new Users();
        users.setUsername(create.getUsername());
        users.setEmail(create.getEmail());
        users.setPassword(create.getPassword());
        return users;
    }
}

