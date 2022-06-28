package com.finalproject.secondhand.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
