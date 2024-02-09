package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.OrderItem;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Orders, Short> {

    @Query(value = "SELECT oi.* FROM orders o " +
            "INNER JOIN order_item oi ON o.order_id = oi.order_id " +
            "INNER JOIN customer c ON o.customer_id = c.customer_id " +
            "WHERE c.customer_id = ?1", nativeQuery = true)
    List<OrderItem> findOrderItemsByCustomerId(short customerId);
}
