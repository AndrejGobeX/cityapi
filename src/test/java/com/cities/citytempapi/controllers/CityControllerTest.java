package com.cities.citytempapi.controllers;

import com.cities.citytempapi.models.City;
import com.cities.citytempapi.models.User;
import com.cities.citytempapi.services.CityService;
import com.cities.citytempapi.services.UserService;
import com.cities.citytempapi.utils.Authorization;
import com.cities.citytempapi.utils.TokenWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static com.cities.citytempapi.controllers.CityController.MESSAGE_CITY_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;

class CityControllerTest {

    TokenWrapper tokenWrapper = new TokenWrapper();
    CityService cityService = new CityService();
    CityController cityController = new CityController(cityService, tokenWrapper);

    private void login(){
        tokenWrapper.setToken(
                Authorization.login(
                        new User(0, "admin", "admin", "admin@admin.com"),
                        new User(0, "admin", "admin", "admin@admin.com")
                )
        );
    }

    private void logout(){
        tokenWrapper.setToken(null);
    }

    @Test
    void getCity1() {
        logout();
        assertEquals(
                cityController.getCity(1).getStatusCode(),
                HttpStatus.UNAUTHORIZED
        );

        login();
        assertEquals(
                cityController.getCity(1).getStatusCode(),
                HttpStatus.OK
        );
        assertEquals(
                cityController.getCity(-1).getStatusCode(),
                HttpStatus.NOT_FOUND
        );
    }

    @Test
    void createCity() {
        logout();
        assertEquals(
                cityController.createCity(
                        new City(0, "City", "Country", "Region", 100)
                ).getStatusCode(),
                HttpStatus.UNAUTHORIZED
        );

        login();
        ResponseEntity<?> response = cityController.createCity(
                new City(0, "City", "Country", "Region", 100)
        );
        assertEquals(
                response.getStatusCode(),
                HttpStatus.CREATED
        );
        assertEquals(
                cityController.getCity("City").getStatusCode(),
                HttpStatus.OK
        );

        response = cityController.createCity(
                new City(0, "City", "Country", "Region", 100)
        );
        assertEquals(
                response.getStatusCode(),
                HttpStatus.CONFLICT
        );
    }

    @Test
    void updateCity() {
        logout();
        assertEquals(
                cityController.updateCity(
                        new City(0, "City", "Country", "Region", 100)
                ).getStatusCode(),
                HttpStatus.UNAUTHORIZED
        );

        login();
        int id = cityService.getCity("City").getId();
        assertEquals(
                cityController.updateCity(
                        new City(id, "City", "Country", "Region", 1000)
                ).getStatusCode(),
                HttpStatus.CREATED
        );
        assertEquals(
                cityController.updateCity(
                        new City(-1, "City", "Country", "Region", 1000)
                ).getStatusCode(),
                HttpStatus.CONFLICT
        );
    }

    @Test
    void deleteCity() {
        logout();
        assertEquals(
                cityController.deleteCity(-1).getStatusCode(),
                HttpStatus.UNAUTHORIZED
        );

        login();
        int id = cityService.getCity("City").getId();
        assertEquals(
                cityController.deleteCity(id).getStatusCode(),
                HttpStatus.OK
        );
        assertEquals(
                cityController.deleteCity(id).getStatusCode(),
                HttpStatus.CONFLICT
        );
    }
}