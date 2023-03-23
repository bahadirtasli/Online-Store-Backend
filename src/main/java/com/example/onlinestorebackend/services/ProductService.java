package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.ProductNotFoundException;
import com.example.onlinestorebackend.models.Product;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public interface ProductService {

    /**
     * To create a new product
     *
     * @param product Product
     */
    void createProduct(Product product);

    /**
     * To find a product by title
     *
     * @param title Title
     * @return Product
     */
    Product findProductByTitle(String title) throws ProductNotFoundException;


    /**
     * To update an existing Product
     * @param product Product
     */
    void updateProduct(Product product) throws ProductNotFoundException;

    /**
     * To delete a Product by title
     * @param title Title
     */
    void deleteProductByTitle(String title) throws ProductNotFoundException;

}
