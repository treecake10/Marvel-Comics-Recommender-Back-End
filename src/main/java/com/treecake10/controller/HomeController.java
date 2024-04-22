package com.treecake10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<String> HomeController() {
        return new ResponseEntity<>("Welcome to the Marvel Comics Recommender project", HttpStatus.OK);
    }
}
