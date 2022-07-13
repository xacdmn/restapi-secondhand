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

    List<Products> findByUsersAndIsSold(Users users, Boolean isSold);
    List<Products> findByUsersAndIsWishlist(Users users, Boolean isWishlist);

    void deleteProductsByProductId(Integer productId);

    @NonNull
    @Query("select p from products p " +
            "where p.isPublished = true " +
            "and p.isWishlist = false " +
            "and p.isSold = false" )
    Page<Products> findAll(@NonNull Pageable pageable);

    @Query("select p from products p " +
            "where upper (p.productName) like upper (concat('%', ?1, '%'))" +
            "and p.isPublished = true " +
            "and p.isWishlist = false " +
            "and p.isSold = false")
    Page<Products> findByProductName(String productName, Pageable pageable);

    @Query("select  p from products p " +
            "where p.categories.id =:categoryId " +
            "and p.isPublished = true " +
            "and p.isWishlist = false " +
            "and p.isSold = false ")
    Page<Products> findByCategory(Integer categoryId, Pageable pageable);

    @Query("select p from products p " +
            "where upper(p.productName) like upper(concat('%', ?1, '%')) " +
            "and upper(p.categories)  like upper(concat('%', ?2, '%'))" +
            "and p.isPublished = true " +
            "and p.isWishlist = false " +
            "and p.isSold = false")
    Page<Products> findByProductNameContainingIgnoreCaseAndCategoryIgnoreCase(String productName, Integer categoryId, Pageable pageable);
}
