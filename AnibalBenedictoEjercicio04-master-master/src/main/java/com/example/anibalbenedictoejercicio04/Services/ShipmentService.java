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
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentService {
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Transactional
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO, Customer customer) {
        // Crear un nuevo envío y establecer el cliente
        Shipment shipment = new Shipment();
        shipment.setCustomer(customer);
        shipment.setShipmentDate(LocalDateTime.now());
        shipment.setCity(shipmentDTO.getCity());
        shipment.setCountry(shipmentDTO.getCountry());
        shipment.setState(shipmentDTO.getState());
        shipment.setZipCode(shipmentDTO.getZipCode());
        shipment.setAddress(customer.getAddress());
        Shipment savedShipment = shipmentRepository.save(shipment);
        List<Orders> orders = orderRepository.findOrdersByCustomer(customer);
        for (Orders order : orders) {
            order.setShipment(savedShipment);
            orderRepository.save(order);
        }
        return ShipmentDTO.fromEntity(savedShipment);
    }
}