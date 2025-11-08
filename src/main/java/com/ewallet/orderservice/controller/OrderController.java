package com.ewallet.orderservice.controller;

import com.ewallet.orderservice.constants.ServiceConstants;
import com.ewallet.orderservice.dto.OrderRequest;
import com.ewallet.orderservice.entity.Order;
import com.ewallet.orderservice.service.OrderService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = Order.builder()
                .customerName(orderRequest.getCustomerName())
                .amount(orderRequest.getAmount())
                .status(orderRequest.getStatus())
                .build();
        Order saved = orderService.createOrder(order);
        logger.info("order created successfully:::::::::::");
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
        Order updated = Order.builder()
                .customerName(orderRequest.getCustomerName())
                .amount(orderRequest.getAmount())
                .status(orderRequest.getStatus())
                .build();
        return ResponseEntity.ok(orderService.updateOrder(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        logger.info("order deleeted successfully:::::::::::");
        return ResponseEntity.ok(ServiceConstants.ORDER_DELETED_SUCCESSFULLY);
    }
}
