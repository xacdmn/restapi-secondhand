package com.finalproject.secondhand.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false)
    @Size(max = 30)
    private String username;

    @Column(name = "email", nullable = false)
    @Size(max = 30)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 8)
    private String password;

    @Column(name = "address")
    @Size(max = 200)
    private String address;

    @Column(name = "phone")
    @Size(min = 12, max = 13)
    private Long phoneNumber;

}
