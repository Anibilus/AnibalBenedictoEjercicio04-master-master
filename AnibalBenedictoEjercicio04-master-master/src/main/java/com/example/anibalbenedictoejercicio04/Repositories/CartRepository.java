package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.Cart;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartRepository extends PagingAndSortingRepository<Product, Short> {
@Query("SELECT c FROM Cart p where c.customerId = ?1")
Page<Cart> findCartByCustomerId(Pageable pageable, Short customerId);
}
