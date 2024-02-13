package com.example.anibalbenedictoejercicio04.Controller;

import com.example.anibalbenedictoejercicio04.DTO.ShipmentDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
import com.example.anibalbenedictoejercicio04.Services.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        ShipmentDTO createdShipment = shipmentService.createShipment(shipmentDTO);
        return ResponseEntity.ok(createdShipment);
    }
}