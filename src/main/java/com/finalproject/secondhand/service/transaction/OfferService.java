package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.response.WishlistResponse;
import org.springframework.stereotype.Component;

@Component
public interface OfferService {

    WishlistResponse findByUser(Users body);
    Offers findByOfferId(Integer offerId);
    void saveOffer(Offers offers);
    void updateStatusOffer(Offers body, Integer offerId);

}
