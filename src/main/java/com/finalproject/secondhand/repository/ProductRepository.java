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

    Page<Products> findByProductNameContaining(String productName, Pageable pageable);
    Page<Products> findByCategoryContaining(String category, Pageable pageable);
    //    Page<Products> findByProductNameAndCategory(String productName, String category, Pageable pageable);
    Page<Products> findByProductNameContainingAndCategoryContaining(String productName, String category, Pageable pageable);
}
