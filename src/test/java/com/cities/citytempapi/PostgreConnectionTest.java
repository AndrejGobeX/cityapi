package com.cities.citytempapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgreConnectionTest {

    @Test
    void getConnection() {
        assertNotNull(PostgreConnection.getConnection());
    }
}