package com.drones.dispatcherdrone.bookstore.ropository;

import com.drones.dispatcherdrone.bookstore.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Override
    @Transactional(readOnly = true)
    @EntityGraph(attributePaths = {"books.author"}, type = EntityGraph.EntityGraphType.FETCH)
    Page<Publisher> findAll(Pageable pageable);
}
