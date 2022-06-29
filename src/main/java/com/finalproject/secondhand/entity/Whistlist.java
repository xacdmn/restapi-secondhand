package com.finalproject.secondhand.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity(name = "whistlist")
public class Whistlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "whistlist_id")
    private Integer whistlistId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

}
