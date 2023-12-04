package com.cities.citytempapi.utils;

import com.cities.citytempapi.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationTest {

    @Test
    void login() {
        assertEquals(
                Authorization.login(
                        new User(0, "admin", "admin", "admin@admin.com"),
                        new User(9, "admin", "admin", "admin@admin.com")
                ).length(),
                64
        );
    }

    @Test
    void authenticate() {
        String token = Authorization.login(
                new User(0, "admin", "admin", "admin@admin.com"),
                new User(9, "admin", "admin", "admin@admin.com")
        );
        assertTrue(Authorization.authenticate(token));
        assertFalse(Authorization.authenticate("sfesfesfeadaedea"));
        assertFalse(Authorization.authenticate(null));
    }
}