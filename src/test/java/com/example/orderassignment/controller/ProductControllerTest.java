package com.example.orderassignment.controller;

import com.example.orderassignment.dto.ProductDTO;
import com.example.orderassignment.entity.Product;
import com.example.orderassignment.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductControllerTest {

    @InjectMocks
    private ProductController mockProductController;
    @Mock
    private ProductService productService;
    @Mock
    private Product product;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProductsTest() {
        ResponseEntity<List<ProductDTO>> response =  mockProductController.getAllProducts();

        assertNotNull(response);
    }

    @Test
    public void createProductTest() throws Exception {
        ResponseEntity<Product> response =  mockProductController.createProduct(product);

        assertNotNull(response);
    }
}
