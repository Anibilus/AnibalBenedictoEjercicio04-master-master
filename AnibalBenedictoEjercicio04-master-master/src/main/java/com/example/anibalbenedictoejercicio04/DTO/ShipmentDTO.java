package com.example.anibalbenedictoejercicio04.DTO;

import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ShipmentDTO {

    private Short shipmentId;
    private LocalDateTime shipmentDate;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private List<Orders> orders;
    private Customer customer;

    public ShipmentDTO(Short shipmentId, LocalDateTime shipmentDate, String address, String city, String state, String country, String zipCode, List<Orders> orders, Customer customer) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.orders = orders;
        this.customer = customer;
    }
    public static ShipmentDTO fromEntity(Shipment shipment){
        return new ShipmentDTO(
                shipment.getShipmentId(),
                shipment.getShipmentDate(),
                shipment.getAddress(),
                shipment.getCity(),
                shipment.getState(),
                shipment.getCountry(),
                shipment.getZipCode(),
                shipment.getOrders(),
                shipment.getCustomer()
        );
    }
}
