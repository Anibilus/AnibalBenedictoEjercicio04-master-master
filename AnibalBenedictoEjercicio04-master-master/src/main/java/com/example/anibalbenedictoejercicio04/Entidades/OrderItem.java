package com.example.anibalbenedictoejercicio04.Entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_item_id", nullable = false)
    @EqualsAndHashCode.Include
    private short orderItemId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "price", nullable = false,precision = 10, scale = 2)
    private BigDecimal price;
    //relacion muchos a uno con orders
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;
    //relacion muchos a uno con product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
