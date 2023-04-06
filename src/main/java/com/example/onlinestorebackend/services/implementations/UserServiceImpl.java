package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.ProductNotFoundException;
import com.example.onlinestorebackend.exceptions.UserNotFoundException;
import com.example.onlinestorebackend.models.Cart;
import com.example.onlinestorebackend.models.Product;
import com.example.onlinestorebackend.models.User;
import com.example.onlinestorebackend.repositories.CartRepository;
import com.example.onlinestorebackend.repositories.UserRepository;
import com.example.onlinestorebackend.services.CartService;
import com.example.onlinestorebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CartService cartService;

    @Override
    public User findUserById(Long id) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return userOptional.get();
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByFullName(String fullName) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByFullName(fullName);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(fullName);
        }
        return optionalUser.get();
    }

    @Override
    public void createUser(User user) {
        Cart cart = new Cart();
        cart.setTotalCost(0L);
        Cart newCart = cartService.createCart(cart);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setCart(newCart);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        if (findUserByFullName(user.getFullName()) !=null) {
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void deleteUserByFullName(String fullName) throws UserNotFoundException {
        User user = findUserByFullName(fullName);
        user.setActive(false);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void restoreUserByFullName(String fullName) throws UserNotFoundException {
        User user = findUserByFullName(fullName);
        user.setActive(true);
        userRepository.saveAndFlush(user);

    }
}
