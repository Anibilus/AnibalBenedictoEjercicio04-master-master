package com.example.anibalbenedictoejercicio04.Services;
import com.example.anibalbenedictoejercicio04.DTO.ProductDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Wishlist;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Repositories.WishlistRepository;
import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }
    public void addProductToWishlist(Short wishlistId, Short productId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            Product product = new Product();
            product.setProductId(productId);
            wishlist.getProducts().add(product);
            wishlistRepository.save(wishlist);
        } else {
            throw new RuntimeException("Lista de Deseo no encontrada");
        }
    }
    public List<ProductDTO> getProductsByWishlistId(Short wishlistId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            List<ProductDTO> productDTOs = wishlist.getProducts().stream()
                    .map(ProductDTO::fromEntity)
                    .collect(Collectors.toList());
            return productDTOs;
        } else {
            return Collections.emptyList();
        }
    }
    public void removeProductFromWishlist(Short wishlistId, Short productId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        Product productToRemove = wishlist.getProducts().stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la lista de deseos"));

        wishlist.getProducts().remove(productToRemove);
        wishlistRepository.save(wishlist);
    }
    public void clearWishlist(Short wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Lista de deseos no encontrada"));
        wishlist.getProducts().clear();
        wishlistRepository.save(wishlist);
    }
}

