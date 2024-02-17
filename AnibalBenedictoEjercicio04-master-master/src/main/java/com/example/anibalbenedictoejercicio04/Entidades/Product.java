package com.example.anibalbenedictoejercicio04.Entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    @EqualsAndHashCode.Include
    private short productId;
    @Basic
    @Column(name = "SKU", nullable = false,length = 100)
    private String sku;
    @Getter
    @Basic
    @Column(name = "description",nullable = false,length = 100)
    private String description;
    @Basic
    @Column(name = "price",nullable = false,precision = 10, scale = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "stock",nullable = false)
    private int stock;
    //relacion de uno a muchos con order_item
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    //relacion uno a muchos con cart
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> carts;
    //relacion muchos a muchos con category
    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Wishlist> wishlists;

}
