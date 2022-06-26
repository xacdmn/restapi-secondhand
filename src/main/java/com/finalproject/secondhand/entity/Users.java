package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity(name = "users")
@Accessors(chain = true)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "image_profil")
    private String imageProfil;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public Users() {
    }

    public Users(SignupDto signupDto) {
        email = signupDto.getEmail();
        username = signupDto.getUsername();
    }

    public Users(UserDto userDto) {
        userId = userDto.getUserId();
        fullname = userDto.getFullname();
        username = userDto.getUsername();
        email = userDto.getEmail();
        city = userDto.getCity();
        address = userDto.getAddress();
        phone = userDto.getPhone();
        imageProfil = userDto.getImageProfil();
    }
}

