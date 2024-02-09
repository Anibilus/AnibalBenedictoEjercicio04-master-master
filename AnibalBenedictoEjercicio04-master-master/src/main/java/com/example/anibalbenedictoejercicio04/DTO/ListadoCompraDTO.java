package com.example.anibalbenedictoejercicio04.DTO;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Entidades.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ListadoCompraDTO {
    private String nameCustomer;
    private short id;
    private String description;
    private String sku;
    private BigDecimal price;
    private int stock;
    private int quantity;
    private BigDecimal cantidadTotal;
    public static ListadoCompraDTO fromEntity(Product product, Cart cart) {
        int cantidad=cart.getQuantity();
        BigDecimal cantidadBigdecimal=BigDecimal.valueOf(cantidad);
        BigDecimal cantidadTotal = product.getPrice().multiply(cantidadBigdecimal);
        return new ListadoCompraDTO(
                cart.getCustomer().getFirstName(),
                product.getProductId(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getStock(),
                cart.getQuantity(),
                cantidadTotal
        );
    }
}
