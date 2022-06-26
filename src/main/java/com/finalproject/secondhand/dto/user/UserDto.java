package com.finalproject.secondhand.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class UserDto {
    @NotBlank
    private Integer userId;
    @NotBlank
    private String fullname;
    @NotBlank
    private String username;
    @Email(message = "Email tidak valid!")
    @NotBlank
    private String email;
    @JsonIgnore
    private String password;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private String imageProfil;

    private Set<String> roles = new HashSet<>();

    public UserDto() {

    }

    public UserDto(Users users) {
        userId = users.getUserId();
        fullname = users.getFullname();
        username = users.getUsername();
        email = users.getEmail();
        password = users.getPassword();
        city = users.getCity();
        address = users.getAddress();
        phone = users.getPhone();
        imageProfil = users.getImageProfil();
        roles = users.getRoles().stream().map(Roles::getRole).map(Enum::name).collect(Collectors.toSet());
    }
}
