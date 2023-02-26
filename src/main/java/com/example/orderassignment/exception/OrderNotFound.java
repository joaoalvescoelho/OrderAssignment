package com.example.orderassignment.exception;

public class OrderNotFound extends RuntimeException {

    public OrderNotFound(String message) {
        super(message);
    }
}
