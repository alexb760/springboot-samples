package com.drones.dispatcherdrone.bookstore.ropository;

import com.drones.dispatcherdrone.bookstore.entities.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
  @Override
  @EntityGraph(value = "bookstore-graph", type = EntityGraph.EntityGraphType.FETCH)
  List<Author> findAll();

  @EntityGraph(
      attributePaths = {"books"},
      type = EntityGraph.EntityGraphType.FETCH)
  List<Author> findAuthorByAgeLessThan(final Long age);

  @EntityGraph(value = "bookstore-author-graph-basic-attributes", type = EntityGraph.EntityGraphType.FETCH)
 List<Author> findAuthorByGenre(final String genre);
}
