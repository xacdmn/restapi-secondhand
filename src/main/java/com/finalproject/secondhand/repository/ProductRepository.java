package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Products, Integer> {

    List<Products> findByCategory(String category);
    List<Products> findByProductName(String productName);
    List<Products> findByUsers(Users users);
    List<Products> findByUsersAndIsSold(Users users, Boolean isSold);

    Page<Products> findAllByIsPublishedContainingAndIsSoldContaining(Boolean isPublished, Boolean isSold, Pageable pageable);
    Page<Products> findByProductNameContainingAndIsPublishedContainingAndIsSoldContaining(String productName, Pageable pageable, Boolean isPublished, Boolean isSold);
    Page<Products> findByCategoryContainingAndIsPublishedContainingAndIsSoldContaining(String category, Pageable pageable, Boolean isPublished, Boolean isSold);
//    Page<Products> findByProductNameAndCategory(String productName, String category, Pageable pageable);
    Page<Products> findByProductNameContainingAndCategoryContainingAndIsPublishedContainingAndIsSoldContaining(String productName, String category, Pageable pageable, Boolean isPublished, Boolean isSold);
}
