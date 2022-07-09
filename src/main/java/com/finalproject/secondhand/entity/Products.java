package com.finalproject.secondhand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Products extends BaseDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private String price;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "image3")
    private String image3;
    @Column(name = "image4")
    private String image4;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @JsonIgnore
//    private List<Notifications> notifications;
    @Column
    private Boolean isPublished= false;
    @Column
    private Boolean isSold= false;

}
