package com.example.anibalbenedictoejercicio04.DTO;

import java.math.BigDecimal;

import com.example.anibalbenedictoejercicio04.Entidades.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private short id;
    private String description;
    private String sku;
    private BigDecimal price;
    private int stock;
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getStock()
        );
    }
}