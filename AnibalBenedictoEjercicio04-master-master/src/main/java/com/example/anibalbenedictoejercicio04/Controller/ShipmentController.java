package com.example.anibalbenedictoejercicio04.Controller;

import com.example.anibalbenedictoejercicio04.DTO.ShipmentDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Repositories.CustomerRepository;
import com.example.anibalbenedictoejercicio04.Services.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final CustomerRepository customerRepository;

    public ShipmentController(ShipmentService shipmentService, CustomerRepository customerRepository) {
        this.shipmentService = shipmentService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ShipmentDTO> createShipment(@PathVariable("customerId") short customerId, @RequestBody ShipmentDTO shipmentDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("El Customer no encuentraado"));
        ShipmentDTO createdShipment = shipmentService.createShipment(shipmentDTO, customer);
        return ResponseEntity.ok(createdShipment);
    }
}
