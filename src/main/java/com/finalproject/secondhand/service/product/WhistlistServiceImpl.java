package com.finalproject.secondhand.service.product;

import com.finalproject.secondhand.entity.Products;
import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.entity.Whistlist;
import com.finalproject.secondhand.repository.ProductRepository;
import com.finalproject.secondhand.repository.UserRepository;
import com.finalproject.secondhand.repository.WhistlistRepository;
import com.finalproject.secondhand.response.WhistlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhistlistServiceImpl implements WhistlistService{

    @Autowired
    private WhistlistRepository whistlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WhistlistResponse findAll() {
        return null;
    }

    @Override
    public void addWhistlist(Integer productId, String username) {
        Whistlist whistlist = new Whistlist();
        Products products = productRepository.getById(productId);
        Users users = userRepository.findByUsername(username);
        whistlist.setProductId(products);
        whistlist.setUserId(users);
        whistlistRepository.save(whistlist);
    }

    @Override
    public void deleteWhistlist() {

    }
}
