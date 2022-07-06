package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Offers;
import org.springframework.stereotype.Component;

@Component
public interface OfferService {

    Offers findByOfferId(Integer offerId);
    void saveOffer(Offers offers);
    void updateStatusOffer(Offers body, Integer offerId);



}
