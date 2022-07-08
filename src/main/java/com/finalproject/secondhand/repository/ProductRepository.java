package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @NonNull
    @Query("select p from products p " +
            "where p.isPublished = true " +
            "and p.isSold = false")
    Page<Products> findAll(@NonNull Pageable pageable);

    @Query("select p from products p " +
            "where upper (p.productName) like upper (concat('%', ?1, '%'))" +
            "and p.isPublished = true " +
            "and p.isSold = false")
    Page<Products> findByProductName(String productName, Pageable pageable);

    @Query("select  p from products p " +
            "where p.category =:category " +
            "and p.isPublished = true " +
            "and p.isSold = false ")
    Page<Products> findByCategory(String category, Pageable pageable);

    @Query("select p from products p " +
            "where upper(p.productName) like upper(concat('%', ?1, '%')) " +
            "and p.category = ?2 " +
            "and p.isPublished = true " +
            "and p.isSold = false")
    Page<Products> findByProductNameContainingIgnoreCaseAndCategoryId(String productName, String category, Pageable pageable);
}
