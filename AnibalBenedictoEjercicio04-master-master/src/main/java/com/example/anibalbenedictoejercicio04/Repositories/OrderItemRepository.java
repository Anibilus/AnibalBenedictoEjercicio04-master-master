package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Short> {
}