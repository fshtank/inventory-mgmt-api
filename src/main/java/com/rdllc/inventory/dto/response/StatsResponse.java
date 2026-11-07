package com.rdllc.inventory.dto.response;

import java.math.BigDecimal;

public class StatsResponse {

    private Long totalProducts;
    private Integer totalStock;
    private BigDecimal averagePrice;

    public StatsResponse() {
    }

    public StatsResponse(Long totalProducts, Integer totalStock, BigDecimal averagePrice) {
        this.totalProducts = totalProducts;
        this.totalStock = totalStock;
        this.averagePrice = averagePrice;
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }
}
