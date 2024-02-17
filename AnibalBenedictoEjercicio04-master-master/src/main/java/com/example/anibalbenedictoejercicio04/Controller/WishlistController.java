package com.example.anibalbenedictoejercicio04.Controller;
import com.example.anibalbenedictoejercicio04.DTO.ProductDTO;
import com.example.anibalbenedictoejercicio04.Services.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/{wishlistId}/addProduct")
    public ResponseEntity<String> addProductToWishlist(@PathVariable Short wishlistId, @RequestBody Map<String, Object> requestBody) {
        Short productId = Short.valueOf(requestBody.get("productId").toString());
        wishlistService.addProductToWishlist(wishlistId, productId);
        return ResponseEntity.ok("El producto añadido correctamente a la Wishlist");
    }
    @GetMapping("/{wishlistId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByWishlistId(@PathVariable Short wishlistId) {
        List<ProductDTO> products = wishlistService.getProductsByWishlistId(wishlistId);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/{wishlistId}/products")
    public ResponseEntity<String> removeProductFromWishlist(@PathVariable Short wishlistId, @RequestBody Map<String, Object> requestBody) {
        try {
            Short productId = Short.valueOf(requestBody.get("productId").toString());
            wishlistService.removeProductFromWishlist(wishlistId, productId);
            return ResponseEntity.ok("El producto a sido removido correctamente");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del producto no es correcto");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{wishlistId}/clear")
    public ResponseEntity<String> clearWishlist(@PathVariable Short wishlistId) {
        try {
            wishlistService.clearWishlist(wishlistId);
            return ResponseEntity.ok("Wishlist a sido vaciada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}