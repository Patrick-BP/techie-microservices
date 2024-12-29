package com.techie.microservices.order_service.services;

import com.techie.microservices.order_service.dto.OrderRequest;
import com.techie.microservices.order_service.dto.OrderResponse;
import com.techie.microservices.order_service.models.Order;
import com.techie.microservices.order_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        order.setPrice(orderRequest.price());
        orderRepository.save(order);

        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getQuantity(), order.getPrice());
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getQuantity(), order.getPrice());
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        order.setPrice(orderRequest.price());
        orderRepository.save(order);

        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getQuantity(), order.getPrice());
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order2 -> new OrderResponse(order2.getId(), order2.getOrderNumber(), order2.getSkuCode(), order2.getQuantity(), order2.getPrice()))
                .collect(Collectors.toList());

    }

}
