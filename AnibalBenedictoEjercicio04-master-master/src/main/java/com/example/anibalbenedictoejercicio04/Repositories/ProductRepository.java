package com.example.anibalbenedictoejercicio04.Repositories;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Short> {

    @Query("SELECT p FROM Product p ORDER BY p.description")
    Page<Product> findPaginatedProducts(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE lower(p.description) LIKE lower(concat('%', ?1, '%'))")
    Page<Product> findByDescription(Pageable pageable, String description);
    @Query("SELECT p FROM Product p WHERE p.productId = ?1")
    Optional<Product> findById(short productId);
}


