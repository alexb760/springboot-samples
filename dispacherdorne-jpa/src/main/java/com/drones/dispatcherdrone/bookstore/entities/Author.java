package com.drones.dispatcherdrone.bookstore.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(
    name = "bookstore-graph",
    attributeNodes = {@NamedAttributeNode(value = "books", subgraph = "publisher-subGraph")},
    subgraphs = {
      @NamedSubgraph(
          name = "publisher-subGraph",
          attributeNodes = {@NamedAttributeNode(value = "publisher")})
    })
@NamedEntityGraph(
    name = "bookstore-author-graph-basic-attributes",
    attributeNodes = {@NamedAttributeNode(value = "name"), @NamedAttributeNode(value = "books")})
@Table(name = "author")
@Getter
@Setter
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  @Basic(fetch = FetchType.LAZY)
  private String genre;
  @Basic(fetch = FetchType.LAZY)
  private Integer age;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "author")
  private List<Book> books = new ArrayList<>();

  @Override
  public String toString() {
    return String.format("Author {id: %d, name: %s, genre: %s, age: %s}", id, name, genre, age);
  }

  @Override
  public int hashCode() {
    return 31 + 2023;
  }
}
