package com.techie.microservices.order_service.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id, String orderNumber, String skuCode, int quantity, BigDecimal price) {
}
