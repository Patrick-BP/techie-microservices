package com.techie.microservices.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public record ProductRequest(String id, String name, String description, BigDecimal price) {}
