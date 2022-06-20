package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.Enum.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity(name = "roles")
public class Roles implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    private Role name;

    public Roles() {
    }
    public Roles(int i, Role buyer){
    }
}
