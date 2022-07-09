package com.finalproject.secondhand.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Notifications extends BaseDate {

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
