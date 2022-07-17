package com.finalproject.secondhand.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PasswordResetToken extends BaseEntity{
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users users;

    private Date expiredDate;

    public PasswordResetToken(Users users, String token){
        this.token=token;
        this.users=users;
    }

    public PasswordResetToken() {

    }
}
