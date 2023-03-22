package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByDescription(String description);

}
