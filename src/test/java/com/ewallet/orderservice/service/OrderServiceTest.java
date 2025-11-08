package com.ewallet.orderservice.service;

import com.ewallet.orderservice.entity.Order;
import com.ewallet.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_shouldSaveAndReturn() {
        Order order = Order.builder().customerName("A").amount(100.0).status("PENDING").build();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order saved = orderService.createOrder(order);
        assertNotNull(saved);
        assertEquals("A", saved.getCustomerName());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void getAllOrders_shouldReturnList() {
        List<Order> list = Arrays.asList(
                Order.builder().customerName("A").amount(10.0).status("P").build(),
                Order.builder().customerName("B").amount(20.0).status("C").build()
        );
        when(orderRepository.findAll()).thenReturn(list);

        List<Order> result = orderService.getAllOrders();
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrderById_whenNotFound_shouldThrow() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.getOrderById(1L));
    }
}
