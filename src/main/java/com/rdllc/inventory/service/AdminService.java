package com.rdllc.inventory.service;

import com.rdllc.inventory.dto.response.StatsResponse;
import com.rdllc.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminService {

    private final ProductRepository productRepository;

    public AdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public StatsResponse getStats() {
        Long totalProducts = productRepository.countProducts();
        Integer totalStock = productRepository.sumStock() != null ? productRepository.sumStock() : 0;
        BigDecimal averagePrice = productRepository.avgPrice() != null ? productRepository.avgPrice() : BigDecimal.ZERO;

        return new StatsResponse(totalProducts, totalStock, averagePrice);
    }
}
