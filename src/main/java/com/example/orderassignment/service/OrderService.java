package com.example.orderassignment.service;


import com.example.orderassignment.dto.AllOrdersDTO;
import com.example.orderassignment.dto.OrderDTO;
import com.example.orderassignment.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@Service
public interface OrderService {
    public List<AllOrdersDTO> getAllOrders();
    public Order getOrderById(Long id);
    public Long createOrder(OrderDTO orderDTO) throws InstanceAlreadyExistsException, JsonProcessingException;

}
