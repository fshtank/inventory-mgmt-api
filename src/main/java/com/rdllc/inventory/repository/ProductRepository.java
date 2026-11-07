package com.rdllc.inventory.repository;

import com.rdllc.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT COUNT(p) FROM Product p")
    Long countProducts();

    @Query("SELECT SUM(p.stock) FROM Product p")
    Integer sumStock();

    @Query("SELECT AVG(p.price) FROM Product p")
    BigDecimal avgPrice();
}
