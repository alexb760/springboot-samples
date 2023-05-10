package com.drones.dispatcherdrone.bookstore;

import com.drones.dispatcherdrone.bookstore.entities.Author;
import com.drones.dispatcherdrone.bookstore.entities.Publisher;
import com.drones.dispatcherdrone.bookstore.service.AuthorService;
import com.drones.dispatcherdrone.bookstore.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @GetMapping
    public List<Author> finAllAuthor(){
    return authorService.getAllAuthorsAndChildrenAdjacent();
  }

  @GetMapping("/publishers")
  public List<Publisher> allPublisers(){
    return publisherService.findAllPublishers();
  }

  @GetMapping("/Age49")
  public List<Author> fetchByAgeLessThan49(){
    return authorService.getAuthorAgeLessthan();
  }
  @GetMapping("/genre")
  public List<Author> fetchBygenre(){
    return authorService.getAuthorByGenre();
  }
}
