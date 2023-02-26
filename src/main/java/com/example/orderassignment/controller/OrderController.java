package com.example.orderassignment.controller;

import com.example.orderassignment.dto.AllOrdersDTO;
import com.example.orderassignment.dto.OrderDTO;
import com.example.orderassignment.entity.Order;
import com.example.orderassignment.exception.AlreadyOrdered;
import com.example.orderassignment.exception.EmailNotFound;
import com.example.orderassignment.exception.OrderNotFound;
import com.example.orderassignment.exception.ProductNotFound;
import com.example.orderassignment.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<AllOrdersDTO>> getAllOrders() {
        try {
            return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        } catch (OrderNotFound e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) throws InstanceAlreadyExistsException, JsonProcessingException {
        try {
            return new ResponseEntity(orderService.createOrder(orderDTO), HttpStatus.CREATED);
        } catch (AlreadyOrdered | EmailNotFound | IllegalArgumentException | ProductNotFound e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
