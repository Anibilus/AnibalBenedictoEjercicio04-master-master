package com.example.anibalbenedictoejercicio04.Services;

import DTO.ProductDTO;
import DTO.WishlistDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Entidades.Wishlist;
import com.example.anibalbenedictoejercicio04.Repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;







    @Service
    public class WishlistService {
        private final WishlistRepository wishlistRepository;
        @Autowired
        public WishlistService(WishlistRepository wishlistRepository) {
            this.wishlistRepository = wishlistRepository;
        }

        public List<WishlistDTO> findWishlistsAndProductsByCustomerId(Short customerId) {
            List<Wishlist> wishlists = wishlistRepository.findByCustomerCustomerId(customerId);

            return wishlists.stream()
                    .map(wishlist -> {
                        ProductDTO productDTO = ProductDTO.fromEntity((Product) wishlist.getProducts());

                        return new WishlistDTO(
                                wishlist.getWhislistId(),
                                wishlist.getWhislistName(),
                                wishlist.getCustomer(),
                                (List<ProductDTO>) productDTO
                        );
                    })
                    .collect(Collectors.toList());
        }
    }



