package com.example.anibalbenedictoejercicio04.Controller;

import com.example.anibalbenedictoejercicio04.DTO.OrderDTO;
import com.example.anibalbenedictoejercicio04.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/createOrder/{customerId}")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Short customerId) {
        OrderDTO createdOrder = orderService.createOrderFromCart(customerId);
        return ResponseEntity.ok(createdOrder);
    }
}

