package com.example.orderassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @Column(name = "orderID", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderID;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
}
