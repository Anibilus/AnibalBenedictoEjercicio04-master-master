package com.example.anibalbenedictoejercicio04.DTO;

import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ShipmentDTO {

    private Short customerId;
    private String city;
    private String state;
    private String country;
    private String zipCode;

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

