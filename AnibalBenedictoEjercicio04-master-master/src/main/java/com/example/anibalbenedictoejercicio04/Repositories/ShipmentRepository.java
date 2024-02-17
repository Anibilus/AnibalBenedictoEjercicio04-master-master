package com.example.anibalbenedictoejercicio04.Repositories;
import com.example.anibalbenedictoejercicio04.Entidades.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Short>{
}
