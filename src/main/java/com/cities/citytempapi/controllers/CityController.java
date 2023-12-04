package com.cities.citytempapi.controllers;

import com.cities.citytempapi.models.City;
import com.cities.citytempapi.services.CityService;
import com.cities.citytempapi.utils.Authorization;
import com.cities.citytempapi.utils.TokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final TokenWrapper tokenWrapper;
    public final static String MESSAGE_CITY_NOT_FOUND =
            "The desired city does not exist in the database, or the query is invalid.";
    public final static String MESSAGE_CITY_CREATED =
            "Target city created successfully.";
    public final static String MESSAGE_CITY_NOT_CREATED =
            "The desired city contains null values, already exists, or an internal error has occurred.";
    public final static String MESSAGE_CITY_UPDATED =
            "Target city updated successfully.";
    public final static String MESSAGE_CITY_NOT_UPDATED =
            "The desired city contains null values, does not exist, or an internal error has occurred.";
    public final static String MESSAGE_CITY_DELETED =
            "Target city deleted successfully.";
    public final static String MESSAGE_CITY_NOT_DELETED =
            "Target city does not exist, or an internal error has occurred.";

    private ResponseEntity<?> formatCity(City city){
        if(city == null)
            return new ResponseEntity<>(
                    MESSAGE_CITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );

        return new ResponseEntity<>(
                city,
                HttpStatus.OK
        );
    }

    private ResponseEntity<?> unauthenticated(){
        return new ResponseEntity<>(
                Authorization.MESSAGE_INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
        );
    }

    @Autowired
    public CityController(CityService cityService, TokenWrapper tokenWrapper){
        this.cityService = cityService;
        this.tokenWrapper = tokenWrapper;
    }

    @GetMapping
    public ResponseEntity<?> getCity(@RequestParam Integer id){
        if(!Authorization.authenticate(tokenWrapper.getToken())){
            return unauthenticated();
        }

        return formatCity(
                cityService.getCity(id)
        );
    }

    @GetMapping("/byName")
    public ResponseEntity<?> getCity(@RequestParam String name){
        if(!Authorization.authenticate(tokenWrapper.getToken())){
            return unauthenticated();
        }

        return formatCity(
                cityService.getCity(name)
        );
    }

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody City city){
        if(!Authorization.authenticate(tokenWrapper.getToken())){
            return unauthenticated();
        }

        if(cityService.createCity(city))
            return new ResponseEntity<>(
                    MESSAGE_CITY_CREATED,
                    HttpStatus.CREATED
            );

        return new ResponseEntity<>(
                MESSAGE_CITY_NOT_CREATED,
                HttpStatus.CONFLICT
        );
    }

    @PutMapping
    public ResponseEntity<?> updateCity(@RequestBody City city){
        if(!Authorization.authenticate(tokenWrapper.getToken())){
            return unauthenticated();
        }

        if(cityService.updateCity(city))
            return new ResponseEntity<>(
                    MESSAGE_CITY_UPDATED,
                    HttpStatus.CREATED
            );

        return new ResponseEntity<>(
                MESSAGE_CITY_NOT_UPDATED,
                HttpStatus.CONFLICT
        );
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCity(@RequestParam Integer id){
        if(!Authorization.authenticate(tokenWrapper.getToken())){
            return unauthenticated();
        }

        if(cityService.deleteCity(id))
            return new ResponseEntity<>(
                    MESSAGE_CITY_DELETED,
                    HttpStatus.OK
            );

        return new ResponseEntity<>(
                MESSAGE_CITY_NOT_DELETED,
                HttpStatus.CONFLICT
        );
    }
}
