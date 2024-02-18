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
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Cart> cartItemsPage = cartRepository.findCartByCustomerId(pageable, customerId);
        List<Cart> cartItems = cartItemsPage.getContent();
        BigDecimal totalPrice = calculateTotalPrice(cartItems);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Orders order = new Orders();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        order.setCustomer(customer);
        Orders savedOrder = orderRepository.save(order);

        for (Cart cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderItemRepository.save(orderItem);
        }
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