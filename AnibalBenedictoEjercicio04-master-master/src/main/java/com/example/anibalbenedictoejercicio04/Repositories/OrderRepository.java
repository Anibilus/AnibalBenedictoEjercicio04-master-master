package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Short> {
    @Query("SELECT o FROM Orders o WHERE o.customer = :customer")
    List<Orders> findOrdersByCustomer(@Param("customer") Customer customer);
}
