package com.example.orderassignment.exception;

public class AlreadyOrdered extends RuntimeException {

    public AlreadyOrdered(String message) {
        super(message);
    }
}