package com.cityapi.cityapi.controllers;

import com.cityapi.cityapi.models.City;
import com.cityapi.cityapi.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/getCityById")
    public City getCity(@RequestParam Integer id){
        return cityService.getCity(id);
    }

    @GetMapping("/getCityByName")
    public City getCity(@RequestParam String name){
        return cityService.getCity(name);
    }
}
