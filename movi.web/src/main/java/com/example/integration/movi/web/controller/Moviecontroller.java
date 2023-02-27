package com.example.integration.movi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Moviecontroller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Moviecontroller.class);

    @PostMapping("/v1/movie")
    public ResponseEntity<String> movie(@RequestBody Movie body){
        LOGGER.info("movies: {}", body);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/v1/movies")
    public ResponseEntity<String> movies(@RequestBody Movie[] body){
        LOGGER.info("movies: {}", body);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
