package com.finalproject.secondhand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
@Accessors(chain = true)
public class UserDetailsImpl implements UserDetails {

    private Integer userId;
    private String fullname;
    private String username;
    @JsonIgnore
    private String email;
    private String password;
    private String city;
    private String address;
    private String phone;
    private String imageProfil;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Users users) {
        this.userId = users.getUserId();
        this.fullname = users.getFullname();
        this.username = users.getUsername();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.city = users.getCity();
        this.address = users.getAddress();
        this.phone = users.getPhone();
        this.authorities = users
                .getRoles()
                .stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
