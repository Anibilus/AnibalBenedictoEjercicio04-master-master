package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.OrderItem;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
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
public interface ShipmentRepository extends CrudRepository<Shipment, Short>{

    @Query("SELECT s FROM Shipment s WHERE s.orders = :orderId")
    List<Shipment> findAllByOrderId(@Param("orderId") Short orderId);

}
