package com.drones.dispatcherdrone.bookstore.service;

import com.drones.dispatcherdrone.bookstore.entities.Author;
import com.drones.dispatcherdrone.bookstore.ropository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepo;

    public List<Author> getAllAuthorsAndChildrenAdjacent(){
        return authorRepo.findAll();
    }

    public List<Author> getAuthorAgeLessthan(){
    return authorRepo.findAuthorByAgeLessThan(45L);
  }
    public List<Author> getAuthorByGenre(){
    return authorRepo.findAuthorByGenre("45L");
  }

}
