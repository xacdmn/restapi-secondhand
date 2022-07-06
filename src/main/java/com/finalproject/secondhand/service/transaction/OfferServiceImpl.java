package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.repository.OfferRepository;
import com.finalproject.secondhand.response.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    OfferRepository offerRepository;

    @Override
    public WishlistResponse findByUser(Users body) {
        Offers offers = offerRepository.findByUsers(body);
        return new WishlistResponse(offers);
    }

    @Override
    public Offers findByOfferId(Integer offerId) {
        return offerRepository.findByOfferId(offerId);
    }

    @Override
    public void saveOffer(Offers body) {
        Offers offers = new Offers();
        offers.setUsers(body.getUsers());
        offers.setProduct(body.getProduct());
        offers.setPriceNegotiated(body.getPriceNegotiated());
        offers.setStatusProcess(body.getStatusProcess());
        offerRepository.save(offers);
    }

    @Override
    public void updateStatusOffer(Offers body, Integer offerId) {
        Offers offers = offerRepository.findByOfferId(offerId);
        offers.setStatusProcess(body.getStatusProcess());
        offerRepository.save(offers);
    }
}
