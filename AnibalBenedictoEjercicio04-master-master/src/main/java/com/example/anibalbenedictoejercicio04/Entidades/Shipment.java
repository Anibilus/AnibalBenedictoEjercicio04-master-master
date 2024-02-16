package com.example.anibalbenedictoejercicio04.Entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Shipment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "shipment_id", nullable = false)
    @EqualsAndHashCode.Include
    private short shipmentId;
    @Basic
    @Column(name = "shipment_date",nullable = false)
    private LocalDateTime shipmentDate;
    @Basic
    @Column(name = "address",nullable = false,length = 100)
    private String address;
    @Basic
    @Column(name = "city",nullable = false,length = 100)
    private String city;
    @Basic
    @Column(name = "state",nullable = false,length = 20)
    private String state;
    @Basic
    @Column(name = "country",nullable = false,length = 50)
    private String country;
    @Basic
    @Column(name = "zip_code",nullable = false,length = 10)
    private String zipCode;
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
