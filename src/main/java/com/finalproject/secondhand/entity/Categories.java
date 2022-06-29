package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.enums.ECategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Enumerated(EnumType.STRING)
    private ECategory category;

    public Categories() {
    }

    public Categories(Integer categoryId, ECategory eCategory) {
        this.categoryId = categoryId;
        this.category = eCategory;
    }
}