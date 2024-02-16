package com.example.anibalbenedictoejercicio04.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.anibalbenedictoejercicio04.Entidades.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer , Short> {
    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId")
    Optional<Customer> findById(@Param("customerId") short customerId);
}
