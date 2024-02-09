package com.example.anibalbenedictoejercicio04.Entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "oreder_id", nullable = false)
    @EqualsAndHashCode.Include
    private Short orderId;
    @Basic
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Basic
    @Column(name = "total_price", nullable = false,precision = 10, scale = 2)
    private BigDecimal totalPrice;
    //relacion de muchos a uno con customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    //relacion de uno a muchos con orderItem
    @OneToMany(mappedBy = "orders", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItems;
    //relacion de muchos a uno payment
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    //relacion de muchos a uno con shipment
    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
