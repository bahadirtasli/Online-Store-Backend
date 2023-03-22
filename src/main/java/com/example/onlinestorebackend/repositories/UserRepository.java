package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByFullName(String fullName);
}
