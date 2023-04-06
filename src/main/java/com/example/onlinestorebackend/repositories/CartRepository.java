package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.Cart;
import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/29/2023
 */
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findById(Long id);

    List<Cart> findAllByOrderLine(OrderLine orderLine);

}
