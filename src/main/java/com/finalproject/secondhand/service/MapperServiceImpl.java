package com.finalproject.secondhand.service;

import com.finalproject.secondhand.dto.user.UserUpdateDto;
import com.finalproject.secondhand.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MapperServiceImpl {

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;


    public Users mapUpdate(UserUpdateDto update) {
        Users users = new Users();
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageService.upload(update.getImageProfil()).getData();
        users.setFullname(update.getFullname());
        users.setCity(update.getCity());
        users.setAddress(update.getAddress());
        users.setPhone(update.getPhone());
        users.setImageProfil(uploadImage.get("url").toString());
        return users;
    }
}
