package com.example.anibalbenedictoejercicio04.Repositories;

import com.example.anibalbenedictoejercicio04.Entidades.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends PagingAndSortingRepository<Cart, Short> {
    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
    Page<Cart> findCartByCustomerId(Pageable pageable, @Param("customerId") short customerId);
    @Query ("select c from Cart c where c.customer.customerId = :customerId and c.product.productId = :productId")
    Optional<Cart> findCustomerIdProductId(@Param("customerId") short customerId, @Param("productId") short productId);
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.customer.customerId = :customerId")
    int vaciarCarrito(@Param("customerId") Short customerId);
    @Transactional
    @Modifying
    @Query("UPDATE Cart c SET c.quantity = CASE WHEN c.quantity > 1 THEN c.quantity - 1 ELSE 0 END " +
            "WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    int removeProductFromCart(@Param("customerId") Short customerId, @Param("productId") Short productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    int deleteProductFromCart(@Param("customerId") Short customerId, @Param("productId") Short productId);
    @Query("SELECT c.quantity FROM Cart c WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    int getProductQuantity(@Param("customerId") Short customerId, @Param("productId") Short productId);
    Cart save(Cart cart);
    }

