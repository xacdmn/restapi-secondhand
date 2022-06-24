package com.finalproject.secondhand.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SigninDto {

    @NotBlank(message = "Username harus diisi!")
    private String username;

    @NotBlank(message = "Password harus diisi!")
    private String password;
}
