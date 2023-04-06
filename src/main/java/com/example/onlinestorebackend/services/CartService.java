package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.CartNotFoundException;
import com.example.onlinestorebackend.exceptions.OrderLineNotFoundException;
import com.example.onlinestorebackend.models.Cart;
import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/29/2023
 */
public interface CartService {

    /**
     * To add OrderLine to the Cart
     *
     * @param orderLine OrderLine
     */
    void addOrderLineToCart(OrderLine orderLine) throws OrderLineNotFoundException;

    /**
     * To remove OrderLine from the Cart
     *
     * @param orderLine OrderLine
     */
    void removeOrderLineFromCart(OrderLine orderLine);

    /**
     * To add orderLine to Cart
     *
     * @param orderLine OrderLine
     */
    void createCartByOrderLine(OrderLine orderLine);

    /**
     * To find active Cart by OrderLine
     * @param orderLine orderLine
     * @return OrderLine
     */
    Cart findActiveCartByOrderLine(OrderLine orderLine);

    /**
     * To create a new cart
     *
     * @param cart Cart
     */
    Cart createCart(Cart cart);

    /**
     * To find a cart by id
     * @param id Cart ID
     * @return Cart
     */
    Cart findCartById(Long id) throws CartNotFoundException;

    /**
     * To find all carts
     *
     * @return list of Cart
     */
    List<Cart> findAllCarts();

    /**
     * To update an existing Cart
     *
     * @param cart Cart
     * @throws CartNotFoundException
     */
    void updateCart(Cart cart) throws CartNotFoundException;


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
