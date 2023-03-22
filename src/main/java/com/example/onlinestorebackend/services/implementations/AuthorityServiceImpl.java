package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.AuthorityNotFoundException;
import com.example.onlinestorebackend.models.Author;
import com.example.onlinestorebackend.repositories.AuthorityRepository;
import com.example.onlinestorebackend.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Author> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Author findAuthorityByFirstName(String firstName) throws AuthorityNotFoundException {
        Optional<Author> optionalAuthority = authorityRepository.findByFirstName(firstName);

        if (optionalAuthority.isEmpty()) {
            throw new AuthorityNotFoundException(firstName);
        }

        return optionalAuthority.get();
    }

    @Override
    public void createAuthority(Author author) {

        author.setActive(true);
        authorityRepository.save(author);

    }
}
