package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.response.WhistlistResponse;
import org.springframework.stereotype.Component;

@Component
public interface WhistlistService {

    WhistlistResponse findAll();
    void addWhistlist(Integer productId, String username);
    void deleteWhistlist();

}
