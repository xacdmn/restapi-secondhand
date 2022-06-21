package com.finalproject.secondhand.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fullname")
    @Size(max = 30)
    private String fullname;

    @Column(name = "username", nullable = false)
    @Size(max = 30)
    private String username;

    @Column(name = "email", nullable = false)
    @Size(max = 30)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 8)
    private String password;

    @Column(name = "city")
    @Size(max = 20)
    private String city;

    @Column(name = "address")
    @Size(max = 200)
    private String address;

    @Column(name = "phone")
    @Size(min = 10)
    private String phone;

    @Column(name = "image_profil")
    private String imageProfil;

    @ManyToMany(fetch = FetchType.EAGER )
    private Collection<Roles> roles = new ArrayList<>();

}

