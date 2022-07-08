package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Offers;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OfferService {

    List<Offers> findByUser(String username);
    Offers findByOfferId(Integer offerId);
    void saveOffer(Offers offers);
    void updateStatusOffer(Offers body, Integer offerId);

}
