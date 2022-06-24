package com.finalproject.secondhand.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UserUpdateDto {

    @NotBlank(message = "Nama harus diisi!")
    private String fullname;
    @NotBlank(message = "Kota harus diisi!")
    private String city;
    @NotBlank(message = "Alamat harus diisi!")
    private String address;
    @NotBlank(message = "Nomor Hp harus diisi!")
    private String phone;
    @NotBlank
    private MultipartFile imageProfil;

}
