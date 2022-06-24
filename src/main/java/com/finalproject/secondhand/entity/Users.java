package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.dto.user.SignupDto;
import com.finalproject.secondhand.dto.user.UserUpdateDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity(name = "users")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String city;
    private String address;
    private String phone;
    private String imageProfil;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();

    public Users() {
    }

    public Users(SignupDto signupDto) {
        email = signupDto.getEmail();
        username = signupDto.getUsername();
    }
}

