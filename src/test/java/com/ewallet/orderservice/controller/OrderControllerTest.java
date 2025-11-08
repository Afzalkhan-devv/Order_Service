package com.ewallet.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ewallet.orderservice.dto.OrderRequest;
import com.ewallet.orderservice.entity.Order;
import com.ewallet.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void createOrder_shouldReturnCreated() throws Exception {
        OrderRequest req = OrderRequest.builder().customerName("Afzal").amount(100.0).status("PENDING").build();
        Order order = Order.builder().id(1L).customerName("Afzal").amount(100.0).status("PENDING").build();
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerName").value("Afzal"));
    }

    @Test
    void createOrder_whenValidationFails_shouldReturnBadRequest() throws Exception {
        OrderRequest req = OrderRequest.builder().customerName("").amount(0.0).status("").build();
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllOrders_shouldReturnOk() throws Exception {
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(
                Order.builder().id(1L).customerName("A").amount(10.0).status("P").build()
        ));
        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }
}
