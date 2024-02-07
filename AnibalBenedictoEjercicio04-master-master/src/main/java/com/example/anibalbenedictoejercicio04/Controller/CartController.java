package com.example.anibalbenedictoejercicio04.Controller;

import DTO.ListadoCompraDTO;
import com.example.anibalbenedictoejercicio04.Services.CartService;
import com.example.anibalbenedictoejercicio04.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/cartOfCustomer/{customerId}")
    public ResponseEntity<List<ListadoCompraDTO>> getCartByCustomerId(
            @PathVariable int customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ListadoCompraDTO> listadoCarrito= cartService.getCartByCustomerId(customerId, page, size);
        return ResponseEntity.ok(listadoCarrito);
    }  @PostMapping("/addProduct")
    public ResponseEntity<List<ListadoCompraDTO>> addProductToCart(
            @RequestParam short customerId,
            @RequestParam short productId,
            @RequestParam int quantity
    ) {
        List<ListadoCompraDTO> listadoCarrito = cartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok(listadoCarrito);
    }
    @DeleteMapping("deleteCart/{customerId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Short customerId) {
        int filasAfectadas = cartService.vaciarCarritoCustomerId(customerId);

        if (filasAfectadas > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{customerId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(
            @PathVariable Short customerId,
            @PathVariable Short productId) {
        return cartService.deleteProductFromCart(customerId, productId);
    }
}
