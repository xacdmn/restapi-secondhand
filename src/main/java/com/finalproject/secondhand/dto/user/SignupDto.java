package com.finalproject.secondhand.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SignupDto {

    @NotBlank(message = "Username harus diisi!")
    private String username;

    @Email(message = "Email tidak valid!")
    @NotBlank(message = "Email harus diisi!")
    private String email;

    @NotBlank(message = "Password harus diisi!")
    private String password;

}
