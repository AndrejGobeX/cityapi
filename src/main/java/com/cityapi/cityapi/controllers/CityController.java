package com.cityapi.cityapi.controllers;

import com.cityapi.cityapi.models.City;
import com.cityapi.cityapi.services.CityService;
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
    private final static String MESSAGE_CITY_NOT_CREATED =
            "The desired city containes null values, already exists, or an internal error has occurred.";

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

    @GetMapping("/getCityById")
    public ResponseEntity<?> getCity(@RequestParam Integer id){
        return formatCity(
                cityService.getCity(id)
        );
    }

    @GetMapping("/getCityByName")
    public ResponseEntity<?> getCity(@RequestParam String name){
        return formatCity(
                cityService.getCity(name)
        );
    }

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody City city){
        if(cityService.createCity(city))
            return new ResponseEntity<>(
                    HttpStatus.CREATED
            );

        return new ResponseEntity<>(
                MESSAGE_CITY_NOT_CREATED,
                HttpStatus.CONFLICT
        );
    }
}
