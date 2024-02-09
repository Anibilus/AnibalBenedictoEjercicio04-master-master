package com.example.anibalbenedictoejercicio04.Services;
import com.example.anibalbenedictoejercicio04.DTO.OrderDTO;
import com.example.anibalbenedictoejercicio04.DTO.OrderItemDTO;
import com.example.anibalbenedictoejercicio04.Entidades.*;
import com.example.anibalbenedictoejercicio04.Repositories.OrderRepository;
import com.example.anibalbenedictoejercicio04.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO createOrder(Short customerId, Short paymentId, Short shipmentId, BigDecimal totalPrice, List<OrderItemDTO> orderItems) {
        // Crear una nueva orden
        Orders order = new Orders();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        // Setear el customer, payment y shipment a partir de los IDs proporcionados
        order.setCustomer(new Customer(customerId));
        order.setPayment(new Payment(paymentId));
        order.setShipment(new Shipment(shipmentId));

        // Guardar la orden en la base de datos
        Orders savedOrder = orderRepository.save(order);

        // Crear los elementos de pedido asociados a esta orden
        List<OrderItem> orderItemsEntities = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(savedOrder);
            Product product = productRepository.findById(orderItemDTO.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(orderItemDTO.getPrice());
            orderItemsEntities.add(orderItem);
        }

        // Guardar los elementos de pedido en la base de datos
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItemsEntities);

        // Convertir la orden guardada y sus elementos de pedido en un DTO
        return OrderDTO.fromEntity(savedOrder);
    }
}


