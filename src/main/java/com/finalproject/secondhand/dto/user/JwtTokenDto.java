package com.finalproject.secondhand.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class JwtTokenDto {
    private String accessToken;
    private String username;
    private String email;
//    private Set<String> roles;

    public JwtTokenDto(String accessToken, UserDto userDto) {
        this.accessToken = accessToken;
        username = userDto.getUsername();
        email = userDto.getEmail();
//        roles = userDto.getRoles().stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    public JwtTokenDto() {

    }
}
