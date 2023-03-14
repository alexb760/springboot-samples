package com.example.integration.movi.web;

import com.example.integration.movi.web.controller.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Headers;
import java.util.Map;
import java.util.Objects;

@MessageEndpoint
public class MovieEndpoint {

  private static final Logger LOG = LoggerFactory.getLogger(MovieEndpoint.class);

  @ServiceActivator
  public void onServiceMovie(Movie movie, @Headers Map<String, Objects> headers) {
    LOG.info("movie: {}", movie);
  }

  @ServiceActivator
  public void onServiceMovies(Movie[] movie, @Headers Map<String, Objects> headers) {
    LOG.info("movie: {}", movie);
  }
}


