package com.example.integration.moviproccesor.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
  By adding this annotation we will make it visible from the Spring container so that you will see
  in the activator service.
 */

@Component
public class MovieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    public String formatMovieFromChannel(String contents){
        LOGGER.info("Formating to Json...");
        String json;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(parseContent(contents));
            LOGGER.info(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private List<Movie> parseContent(String contents) {
        List<Movie> movies = new ArrayList<>();
        String[] record = null;
        for (String line : contents.split(System.getProperty("line.separator"))){
            record = Arrays.stream(line.split(",")).map(String::trim).toArray(String[]::new);
            movies.add(new Movie(record[0],record[1], Integer.valueOf(record[2])));
        }
        return movies;
    }
}
