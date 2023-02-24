package com.example.integration.moviproccesor.core;

import com.example.integration.moviproccesor.core.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@MessageEndpoint
public class MovieEndpoint {

    private final MovieService movieService;
    public MovieEndpoint(MovieService movieService) {
        this.movieService = movieService;
    }
    private static final Logger log = LoggerFactory.getLogger(MovieEndpoint.class);
    @ServiceActivator
    public void process(File input, @Headers Map<String, Objects> headers) throws IOException {
        FileInputStream inputStream = new FileInputStream(input);
//        String movie = new String(StreamUtils.copyToByteArray(inputStream));
        String movie = movieService.formatMovieFromChannel(new String(StreamUtils.copyToByteArray(inputStream)));
        inputStream.close();
        log.info(movie);
    }
}
