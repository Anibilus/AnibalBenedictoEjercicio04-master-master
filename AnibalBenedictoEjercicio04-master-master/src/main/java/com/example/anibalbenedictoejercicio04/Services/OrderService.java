package com.example.anibalbenedictoejercicio04.Services;
import com.example.anibalbenedictoejercicio04.DTO.OrderDTO;
import com.example.anibalbenedictoejercicio04.Entidades.*;
import com.example.anibalbenedictoejercicio04.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.example.anibalbenedictoejercicio04.Repositories.CustomerRepository;
@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(CartRepository cartRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public OrderDTO createOrderFromCart(Short customerId) {
        // Crear un objeto Pageable para obtener todos los elementos del carrito del cliente
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        // Obtener los elementos del carrito del cliente
        Page<Cart> cartItemsPage = cartRepository.findCartByCustomerId(pageable, customerId);
        // Convertir la página a una lista
        List<Cart> cartItems = cartItemsPage.getContent();

        // Calcular el precio total de la orden
        BigDecimal totalPrice = calculateTotalPrice(cartItems);

        // Obtener el cliente
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Crear una nueva orden y establecer el cliente
        Orders order = new Orders();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        order.setCustomer(customer); // Establecer el cliente en la orden

        Orders savedOrder = orderRepository.save(order);

        // Crear los elementos de pedido asociados a esta orden
        for (Cart cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderItemRepository.save(orderItem);
        }
        // Vaciar el carrito después de crear la orden
        cartRepository.vaciarCarrito(customerId);

        return OrderDTO.fromEntity(savedOrder);
    }
    private BigDecimal calculateTotalPrice(List<Cart> cartItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cartItem : cartItems) {
            BigDecimal itemPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
        }
        return totalPrice;
    }
}