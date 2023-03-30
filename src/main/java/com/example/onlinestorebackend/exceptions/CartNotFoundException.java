package com.example.onlinestorebackend.exceptions;

/**
 * @author Bahadir Tasli
 * @Date 3/29/2023
 */
public class CartNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public CartNotFoundException(Long id) {
        super(String.format("ShoppingCart not found for id : %d" , id));
    }

}
