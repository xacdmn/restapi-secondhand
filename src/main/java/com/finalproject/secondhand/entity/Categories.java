package com.finalproject.secondhand.entity;

import com.finalproject.secondhand.enums.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public Categories() {
    }

    public Categories(CategoryEnum categoryEnum) {
        this.category = categoryEnum;
    }
}