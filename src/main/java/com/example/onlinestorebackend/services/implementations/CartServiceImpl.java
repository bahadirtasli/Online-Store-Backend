package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.CartNotFoundException;
import com.example.onlinestorebackend.exceptions.OrderLineNotFoundException;
import com.example.onlinestorebackend.models.Cart;
import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;
import com.example.onlinestorebackend.repositories.CartRepository;
import com.example.onlinestorebackend.repositories.ProductRepository;
import com.example.onlinestorebackend.services.CartService;
import com.example.onlinestorebackend.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/29/2023
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addOrderLineToCart(OrderLine orderLine) throws OrderLineNotFoundException {

    }

    @Override
    public void removeOrderLineFromCart(OrderLine orderLine) {

    }

    @Override
    public void createCartByOrderLine(OrderLine orderLine) {
        try {
            Cart cart = findCartById(orderLine.getId());
            cart.setTotalCost(orderLine.getProductPrice());
            cartRepository.saveAndFlush(cart);
        } catch (RuntimeException | CartNotFoundException exception) {
            Cart cart = new Cart();
            cart.setOrderLine(cart.getOrderLine());
            cart.setTotalCost(orderLine.getProductPrice());
            orderLine.setActive(true);
            cartRepository.save(cart);
        }
    }

    @Override
    public Cart findActiveCartByOrderLine(OrderLine orderLine) {

        Optional<Cart> cartOptional = cartRepository.findAllByOrderLine(orderLine).stream()
                .filter(Cart::isActive)
                .findFirst();

        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found for given product");
        }
        return cartOptional.get();
    }

    @Override
    public Cart findCartById(Long id) throws CartNotFoundException {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (cartOptional.isEmpty()) {
            throw new CartNotFoundException(id);
        }
        return cartOptional.get();
    }


    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart createCart(Cart cart) {
        cart.setActive(true);
        return cartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) throws CartNotFoundException {

        if (findCartById(cart.getId()) != null) {
            cartRepository.saveAndFlush(cart);
        }
    }



    @Override
    public void deleteCartById(Long id) throws CartNotFoundException {
        Cart cart = findCartById(id);
        cart.setActive(false);
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void restoreCartById(Long id) throws CartNotFoundException {
        Cart cart = findCartById(id);
        cart.setActive(true);
        cartRepository.saveAndFlush(cart);
    }

}
