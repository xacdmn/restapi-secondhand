package com.finalproject.secondhand.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "kategori")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @Column(nullable = false, length = 10)
    private String categoryName;
}

