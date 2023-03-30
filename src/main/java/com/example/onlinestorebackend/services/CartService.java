package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.CartNotFoundException;
import com.example.onlinestorebackend.models.Cart;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/29/2023
 */
public interface CartService {

    /**
     * To find all carts
     *
     * @return list of Cart
     */
    List<Cart> findAllCarts();


    /**
     * To create a new cart
     *
     * @param cart Cart
     */
    void createCart(Cart cart);

    /**
     * To update an existing Cart
     *
     * @param cart Cart
     * @throws CartNotFoundException
     */
    void updateCart(Cart cart) throws CartNotFoundException;

    /**
     * To update an existing Cart
     * @param id Id
     * @return Cart
     */
    Cart findCartById(Long id) throws CartNotFoundException;

    /**
     * To delete cart by id
     * @param id Id
     */
    void deleteCartById(Long id) throws CartNotFoundException;

    /**
     * To restore cart by id
     * @param id Id
     */
    void restoreCartById(Long id) throws CartNotFoundException;

}
