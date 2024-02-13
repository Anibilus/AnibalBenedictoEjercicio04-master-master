package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Short> {


    //query para obtener todos los productos de una orden
    @Query("SELECT oi FROM OrderItem oi WHERE oi.orders = :orderId")
    List<OrderItem> findAllByOrderId(@Param("orderId") Short orderId);

}