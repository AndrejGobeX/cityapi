package com.cities.citytempapi.controllers;

import com.cities.citytempapi.models.City;
import com.cities.citytempapi.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final static String MESSAGE_CITY_NOT_FOUND =
            "The desired city does not exist in the database, or the query is invalid.";
    private final static String MESSAGE_CITY_CREATED =
            "Target city created successfully.";
    private final static String MESSAGE_CITY_NOT_CREATED =
            "The desired city contains null values, already exists, or an internal error has occurred.";
    private final static String MESSAGE_CITY_UPDATED =
            "Target city updated successfully.";
    private final static String MESSAGE_CITY_NOT_UPDATED =
            "The desired city contains null values, does not exist, or an internal error has occurred.";
    private final static String MESSAGE_CITY_DELETED =
            "Target city deleted successfully.";
    private final static String MESSAGE_CITY_NOT_DELETED =
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

    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<?> getCity(@RequestParam Integer id){
        return formatCity(
                cityService.getCity(id)
        );
    }

    @GetMapping("/byName")
    public ResponseEntity<?> getCity(@RequestParam String name){
        return formatCity(
                cityService.getCity(name)
        );
    }

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody City city){
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
