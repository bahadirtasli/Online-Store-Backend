package com.example.onlinestorebackend.components;

import com.example.onlinestorebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */

@Component
public class DataInit {

    @Autowired
    private UserService userService;

    public void init () {

        initUser();
    }

    private void initUser () {

    }


}
