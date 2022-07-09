package com.finalproject.secondhand.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Offers offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Users users;

    private Boolean isRead = false;

}
