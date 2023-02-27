package com.example.orderassignment.controller;

import com.example.orderassignment.dto.AllOrdersDTO;
import com.example.orderassignment.dto.OrderDTO;
import com.example.orderassignment.entity.Order;
import com.example.orderassignment.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController mockOrderController;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderDTO orderDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllOrdersTest() {
        ResponseEntity<List<AllOrdersDTO>> response =  mockOrderController.getAllOrders();

        assertNotNull(response);
    }

    @Test
    public void createOrderTest() throws Exception {
        ResponseEntity<Order> response =  mockOrderController.createOrder(orderDTO);

        assertNotNull(response);
    }
}
