package com.example.anibalbenedictoejercicio04.Services;

import com.example.anibalbenedictoejercicio04.Repositories.CartRepository;
import com.example.anibalbenedictoejercicio04.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    //getCartByCustomerId



}
