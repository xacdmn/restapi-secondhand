package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.enums.ERole;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "roles")
@Accessors(chain = true)
@ToString
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public Roles() {

    }

    public Roles(ERole role) {
        this.role = role;
    }
}
