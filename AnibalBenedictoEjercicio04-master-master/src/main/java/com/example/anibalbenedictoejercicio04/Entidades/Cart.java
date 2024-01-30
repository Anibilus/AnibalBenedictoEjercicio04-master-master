package com.example.anibalbenedictoejercicio04.Entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    @EqualsAndHashCode.Include
    private short cartId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    //relacion de muchos a uno con customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    //relacion de muchos a uno con product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}