package com.cities.citytempapi.controllers;

import com.cities.citytempapi.models.User;
import com.cities.citytempapi.services.UserService;
import com.cities.citytempapi.utils.Authorization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void login() {
        UserController userController = new UserController(new UserService());
        assertEquals(
                userController.login(new User(0, "admin", "admin", "admin@admin")).getBody().toString().length(),
                64
        );
        assertEquals(
                Authorization.MESSAGE_WRONG_CRED,
                userController.login(new User(0, "wrong", "wrong", null)).getBody().toString()
        );
    }
}