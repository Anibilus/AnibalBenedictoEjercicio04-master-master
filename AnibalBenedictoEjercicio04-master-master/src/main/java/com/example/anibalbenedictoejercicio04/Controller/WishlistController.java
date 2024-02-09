package com.example.anibalbenedictoejercicio04.Controller;

import com.example.anibalbenedictoejercicio04.Services.WishlistService;
import com.example.anibalbenedictoejercicio04.DTO.WishlistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



    @RestController
    @RequestMapping("/wishlist")
    public class WishlistController {

        private final WishlistService wishlistService;

        @Autowired
        public WishlistController(WishlistService wishlistService) {
            this.wishlistService = wishlistService;
        }

        @GetMapping("/{customerId}")
        public ResponseEntity<List<WishlistDTO>> getWishlistsAndProducts(@PathVariable Short customerId) {
            List<WishlistDTO> wishlistsAndProducts = wishlistService.findWishlistsAndProductsByCustomerId(customerId);
            return new ResponseEntity<>(wishlistsAndProducts, HttpStatus.OK);
        }
    }



