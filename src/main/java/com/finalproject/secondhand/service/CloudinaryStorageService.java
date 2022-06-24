package com.finalproject.secondhand.service;

import com.finalproject.secondhand.result.DataResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface CloudinaryStorageService {

    DataResult<?> upload(MultipartFile multipartFile);

    DataResult<?> delete(String publicIdOfImage);

}
