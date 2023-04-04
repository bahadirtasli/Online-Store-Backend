package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 4/3/2023
 */
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findAllByProduct(Product product);


}
