package com.example.orderassignment.controller;

import com.example.orderassignment.dto.ProductDTO;
import com.example.orderassignment.entity.Product;
import com.example.orderassignment.exception.AlreadyExists;
import com.example.orderassignment.exception.ProductNotFound;
import com.example.orderassignment.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (ProductNotFound e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws JsonProcessingException {
        try {
            return new ResponseEntity(productService.createProduct(product), HttpStatus.CREATED);
        } catch (JsonProcessingException | AlreadyExists | InstanceAlreadyExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
