package com.finalproject.secondhand.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min = 12, max = 13)
    private Long phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRoles",
            joinColumns = @JoinColumn(name = "Id"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> roles = new HashSet<>();

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Users() {

    }
}

