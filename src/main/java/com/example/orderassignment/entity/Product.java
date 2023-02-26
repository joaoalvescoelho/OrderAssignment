package com.example.orderassignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @Column(name = "productID", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long productID;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        this.orders.add(order);
    }

}
