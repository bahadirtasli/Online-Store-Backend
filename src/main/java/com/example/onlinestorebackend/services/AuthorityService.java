package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.AuthorityNotFoundException;
import com.example.onlinestorebackend.models.Author;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public interface AuthorityService {
    /**
     * To find all authorities
     *
     * @return a list of authorities
     */
    List<Author> findAllAuthorities();

    /**
     * To find authority by firstName
     *
     * @param firstName Authority firstName
     * @return Authority
     */
    Author findAuthorityByFirstName(String firstName) throws AuthorityNotFoundException;

    /**
     * To create a new authority
     *
     * @param author Authority
     */
    void createAuthority(Author author);

}
