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
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id", nullable = false)
    @EqualsAndHashCode.Include
    private short paymentId;
    @Basic
    @Column(name = "payment_date",nullable = false)
    private LocalDateTime paymentDate;
    @Basic
    @Column(name = "payment_method",nullable = false,length = 100)
    private String paymentMethod;
    @Basic
    @Column(name = "amount",nullable = false,precision = 10, scale = 2)
    private BigDecimal amount;
    //relacion muchos a uno con customer
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    //relacion uno a muchos con orders
    @OneToMany(mappedBy = "payment")
    private List<Orders> orderItems;
}
