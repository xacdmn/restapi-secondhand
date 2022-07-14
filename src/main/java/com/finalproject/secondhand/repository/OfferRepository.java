package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.EStatusProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offers, Integer> {

    Offers findByOfferId(Integer offerId);
    List<Offers> findOffersByProduct_ProductId(Integer products);
    List<Offers> findByUsers(Users users);
    List<Offers> findByProductAndStatusProcess(Products products, EStatusProcess status);
    List<Offers> findOffersByProduct_UsersAndStatusProcess(Users users, EStatusProcess status);

}