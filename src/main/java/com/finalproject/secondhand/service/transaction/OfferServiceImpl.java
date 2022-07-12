package com.finalproject.secondhand.service.transaction;

import com.finalproject.secondhand.entity.Offers;
import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.enums.EStatusProcess;
import com.finalproject.secondhand.repository.OfferRepository;
import com.finalproject.secondhand.repository.ProductRepository;
import com.finalproject.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Offers> findOffersByProductUsersAndStatusProcess(Users users) {
        EStatusProcess status = EStatusProcess.WAITING;
        return offerRepository.findOffersByProduct_UsersAndStatusProcess(users, status);
    }

    @Override
    public List<Offers> findByUser(String username) {
        Users users = userRepository.findByUsername(username);
        return offerRepository.findByUsers(users);
    }

    @Override
    public Offers findByOfferId(Integer offerId) {
        return offerRepository.findByOfferId(offerId);
    }

    @Override
    public String whatsappSender(Integer offerId) {
        Offers offers = offerRepository.findByOfferId(offerId);
        if (offers.getStatusProcess() == EStatusProcess.ACCEPTED) {
            String numberPhone = offers.getUsers().getPhone();
            String apiWhatsapp = "https://api.whatsapp.com/send?phone=";
            return apiWhatsapp+numberPhone;
        }else {
         return "Product not list in status offer accepted";
        }
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
    public void updateStatusOffer(Products body1, Offers body2, Integer offerId) {
        productRepository.save(body1);
        offerRepository.save(body2);
    }
}
