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

    private Short customerId;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public ShipmentDTO(Short customerId, String city, String state, String country, String zipCode) {
        this.customerId = customerId;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public static ShipmentDTO fromEntity(Shipment shipment){
        Customer customer = shipment.getCustomer();
        return new ShipmentDTO(
                customer.getCustomerId(),
                shipment.getCity(),
                shipment.getState(),
                shipment.getCountry(),
                shipment.getZipCode()
        );
    }
}

