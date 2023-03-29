package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.AuthorNotFoundException;
import com.example.onlinestorebackend.models.Author;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public interface AuthorService {
    /**
     * To find all authorities
     *
     * @return a list of authorities
     */
    List<Author> findAllAuthorities();

    /**
     * To find author by firstName
     *
     * @param name Author name
     * @return Author
     */
    Author findAuthorByName(String name) throws AuthorNotFoundException;

    /**
     * To create a new author
     *
     * @param author Author
     */
    void createAuthor(Author author);

}
