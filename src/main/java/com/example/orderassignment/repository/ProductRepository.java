package com.example.orderassignment.repository;

import com.example.orderassignment.entity.Product;
import com.example.orderassignment.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = ?1 ")
    Product findByName(String name);
}
