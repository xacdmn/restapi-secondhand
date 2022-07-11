package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.enums.ECategory;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Enumerated(EnumType.STRING)
    private ECategory eCategory;
}
