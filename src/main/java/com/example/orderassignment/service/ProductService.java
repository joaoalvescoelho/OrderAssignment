package com.example.orderassignment.service;

import com.example.orderassignment.dto.ProductDTO;
import com.example.orderassignment.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@Service
public interface ProductService {

    public List<ProductDTO> getAllProducts();
    public Product getProductById(Long id);
    public Product createProduct(Product product) throws InstanceAlreadyExistsException, JsonProcessingException;
}
