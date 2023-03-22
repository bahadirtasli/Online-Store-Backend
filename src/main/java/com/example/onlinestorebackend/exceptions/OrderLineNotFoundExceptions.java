package com.example.onlinestorebackend.exceptions;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
public class OrderLineNotFoundExceptions extends Exception{
    private static final long serialVersionUID = 1L;

    public OrderLineNotFoundExceptions(Long id){
        super(String.format("Cart not found for id : %d ", id));

    }
}
