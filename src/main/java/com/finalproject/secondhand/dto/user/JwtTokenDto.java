package com.finalproject.secondhand.dto.user;

import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.entity.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class JwtTokenDto {
    private String accessToken;
    private String username;
    private String email;
    private String role;

    public JwtTokenDto(String accessToken, Users users) {
        this.accessToken = accessToken;
        username = users.getUsername();
        email = users.getEmail();
        role = users.getRoles().stream().map(Roles::getRole).map(Enum::name).map(String::toLowerCase).collect(Collectors.toList()).toString();

    }

    public JwtTokenDto() {

    }
}
