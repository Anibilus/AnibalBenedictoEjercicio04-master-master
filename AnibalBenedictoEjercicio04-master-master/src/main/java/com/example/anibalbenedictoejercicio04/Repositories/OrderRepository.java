package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.OrderItem;
import com.example.anibalbenedictoejercicio04.Entidades.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Short> {

    //query para obtener la orden de un customer por id
    @Query("SELECT o FROM Orders o WHERE o.customer = ?1")
    List<Orders> findByCustomerId(Short customerId);
    //findOrdersByCustomerId query
    @Query("SELECT o FROM Orders o WHERE o.customer = ?1")
    List<Orders> findOrdersByCustomerId(Short customerId);
}
