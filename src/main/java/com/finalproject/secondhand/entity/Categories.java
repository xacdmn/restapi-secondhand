package com.finalproject.secondhand.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity(name = "category")
public class Categories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "name")
    private String categoryName;
}

