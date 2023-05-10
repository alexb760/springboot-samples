package com.drones.dispatcherdrone.bookstore.service;

import com.drones.dispatcherdrone.bookstore.entities.Publisher;
import com.drones.dispatcherdrone.bookstore.ropository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepo;

    public List<Publisher> findAllPublishers(){
        Pageable page = PageRequest.of(0, 10);
    return publisherRepo.findAll(page).getContent();
  }
}
