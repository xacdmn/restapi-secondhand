package com.finalproject.secondhand.dto.user;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Accessors(chain = true)// Untuk chaining
public class SignupDto {

    @NotBlank(message = "Username harus diisi!")
    private String username;

    @Email(message = "Email tidak valid!")
    @NotBlank(message = "Email harus diisi!")
    private String email;

    @NotBlank(message = "Password harus diisi!")
    private String password;

    private Set<String> roles = new HashSet<>();


}
