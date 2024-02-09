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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",nullable = false)
    @EqualsAndHashCode.Include
    private short customerId;
    @Basic
    @Column(name = "first_name",nullable = false, length = 100)
    private String firstName;
    @Basic
    @Column(name = "last_name",nullable = false, length = 100)
    private String lastName;
    @Basic
    @Column(name = "email",nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "password",nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "address",nullable = false, length = 100)
    private String address;
    @Basic
    @Column(name = "phone_number",nullable = false, length = 100)
    private String phoneNumber;

    //relacion uno a muchos con shipment
    @OneToMany(mappedBy = "customer")
    private List<Shipment> shipment;
    //relacion uno a muchos con cart
    @OneToMany(mappedBy = "customer")
    private List<Cart> cart;
    //relacion uno a muchos con orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;
    //relacion uno a muchos con Payment
    @OneToMany(mappedBy = "customer")
    private List<Payment> payment;
    //relacion uno a muchos con whishlist
    @OneToMany(mappedBy = "customer")
    private List<Wishlist> whislist;
}
