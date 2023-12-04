package com.cities.citytempapi.controllers;

import com.cities.citytempapi.models.User;
import com.cities.citytempapi.services.UserService;
import com.cities.citytempapi.utils.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        String token = Authorization.login(user, userService);
        return new ResponseEntity<>(
                token,
                HttpStatus.OK
        );
    }
}
