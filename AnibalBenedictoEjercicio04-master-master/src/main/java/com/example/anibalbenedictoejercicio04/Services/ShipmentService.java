package com.example.anibalbenedictoejercicio04.Services;

import com.example.anibalbenedictoejercicio04.DTO.OrderDTO;
import com.example.anibalbenedictoejercicio04.DTO.ShipmentDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
import com.example.anibalbenedictoejercicio04.Repositories.CustomerRepository;
import com.example.anibalbenedictoejercicio04.Repositories.OrderRepository;
import com.example.anibalbenedictoejercicio04.Repositories.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(OrderRepository orderRepository, CustomerRepository customerRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Transactional
    public ShipmentDTO createShipment(short customerId, String city, String country, String state, String zip) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Crear un nuevo envío y establecer el cliente
        Shipment shipment = new Shipment();
        shipment.setCustomer(customer);
        shipment.setShipmentDate(LocalDateTime.now());
        shipment.setCity(city);
        shipment.setCountry(country);
        shipment.setState(state);
        shipment.setZipCode(zip);
        shipment.setAddress(customer.getAddress());

        Shipment savedShipment = shipmentRepository.save(shipment);

        // Actualizar el shipment_id de las órdenes asociadas al cliente
        List<Orders> orders = orderRepository.findOrdersByCustomerId(customerId);
        for (Orders order : orders) {
            order.setShipment(savedShipment);
            orderRepository.save(order);
        }
        return ShipmentDTO.fromEntity(savedShipment);
    }
}