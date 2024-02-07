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
public class Wishlist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "whislist_id", nullable = false)
    @EqualsAndHashCode.Include
    private short whislistId;
    @Basic
    @Column(name = "wishlist_name",nullable = false,length = 100)
    private String whislistName;
    //relacion muchos a uno con customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    //relacion uno a muchos con product
    @ManyToMany
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
