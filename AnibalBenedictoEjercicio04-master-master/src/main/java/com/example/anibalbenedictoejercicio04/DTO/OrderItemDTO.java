package com.example.anibalbenedictoejercicio04.DTO;
import com.example.anibalbenedictoejercicio04.Entidades.OrderItem;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
public class OrderItemDTO {
    private final Product product;
    private Short productId;
    private int quantity;
    private BigDecimal price;

    public OrderItemDTO(short orderItemId, Orders orders, BigDecimal price, int quantity, Product product) {
        this.productId = orderItemId;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }


    public static OrderItemDTO fromEntity(OrderItem orderitem){
        return new OrderItemDTO(
                orderitem.getOrderItemId(),
                orderitem.getOrders(),
                orderitem.getPrice(),
                orderitem.getQuantity(),
                orderitem.getProduct()
        );
    }
}