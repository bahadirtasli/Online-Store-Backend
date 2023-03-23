package com.example.onlinestorebackend.components;

import com.example.onlinestorebackend.exceptions.AuthorNotFoundException;
import com.example.onlinestorebackend.exceptions.UserNotFoundException;
import com.example.onlinestorebackend.models.Author;
import com.example.onlinestorebackend.models.Role;
import com.example.onlinestorebackend.models.User;
import com.example.onlinestorebackend.services.AuthorService;
import com.example.onlinestorebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.example.onlinestorebackend.utils.Constants.Security.AUTHORITY_ADMIN;
import static com.example.onlinestorebackend.utils.Constants.Security.AUTHORITY_USER;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */

@Component
public class DataInit {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @PostConstruct
    public void init () {
        initUser();
        initAuthor();

    }

    private void initAuthor() {
        System.out.println("Starting initializing Author..");
        Author authorAdmin = new Author();
        authorAdmin.setFirstName(AUTHORITY_ADMIN);
        createAuthor(authorAdmin);

        Author authorUser = new Author();
        authorUser.setFirstName(AUTHORITY_USER);
        createAuthor(authorUser);
    }

    private void initUser() {
        System.out.println("Starting initializing User..");

        try {
            Author author = authorService.findAuthorByFirstName(AUTHORITY_USER);

            User user = new User();
            user.setFullName("bahadir");
            user.setPassword("12345");
            user.setAuthor(author);
            //user.setRole(Role.CUSTOMER);

            try {
                User resultUser = userService.findUserByFullName(user.getFullName());
                System.out.println("Cannot pre-initialize user: " + user.getFullName());
            } catch(UserNotFoundException e) {
                userService.createUser(user);
            }
        } catch (AuthorNotFoundException e) {
            System.out.println("Cannot pre-initialize User! Reason:  " + e.getLocalizedMessage());
        }
    }

    private void createAuthor(Author author) {
        try {
            Author resultAuthor = authorService.findAuthorByFirstName(author.getFirstName());
            System.out.println("Can not pre-initialize author : " + author.getFirstName());
        } catch (AuthorNotFoundException e){
            authorService.createAuthor(author);
        }
    }


}
