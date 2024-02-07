package com.example.anibalbenedictoejercicio04.Repositories;
import com.example.anibalbenedictoejercicio04.Entidades.Wishlist;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.anibalbenedictoejercicio04.Entidades.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Short> {

    List<Wishlist> findByCustomerCustomerId(Short customerId);

    @Query("SELECT w FROM Wishlist w JOIN FETCH w.products p WHERE w.customer.customerId = :customerId")
    List<Wishlist> findWishlistsAndProductsByCustomerId(@Param("customerId") Short customerId);
}


