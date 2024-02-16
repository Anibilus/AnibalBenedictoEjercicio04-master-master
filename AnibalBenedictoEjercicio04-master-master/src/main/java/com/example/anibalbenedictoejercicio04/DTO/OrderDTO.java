package com.example.anibalbenedictoejercicio04.DTO;

import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import com.example.anibalbenedictoejercicio04.Entidades.Payment;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class OrderDTO {
    private final BigDecimal totalPrice;
    private Short customerId;
    private Short paymentId;
    private Short shipmentId;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(Short orderId, Payment payment, LocalDateTime orderDate, BigDecimal totalPrice, Customer customer, Shipment shipment, BigDecimal totalPrice1) {
        this.customerId = customer.getCustomerId();
        if (payment != null) {
            this.paymentId = payment.getPaymentId();
        }
        // Verificar si shipment no es nulo antes de acceder a shipmentId
        if (shipment != null) {
            this.shipmentId = shipment.getShipmentId();
        }
        this.totalPrice = totalPrice;
    }

    public static OrderDTO fromEntity(Orders order){
        return new OrderDTO(
                order.getOrderId(),
                order.getPayment(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getCustomer(),
                order.getShipment(),
                order.getTotalPrice()
        );
    }
}