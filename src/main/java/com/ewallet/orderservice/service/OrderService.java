package com.ewallet.orderservice.service;

import com.ewallet.orderservice.constants.ServiceConstants;
import com.ewallet.orderservice.entity.Order;
import com.ewallet.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException(ServiceConstants.ORDER_NOT_FOUND + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order updateOrder(Long id, Order updated) {
        Order existing = getOrderById(id);
        existing.setCustomerName(updated.getCustomerName());
        existing.setAmount(updated.getAmount());
        existing.setStatus(updated.getStatus());
        logger.info("updating order:::::::");
        return orderRepository.save(existing);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
