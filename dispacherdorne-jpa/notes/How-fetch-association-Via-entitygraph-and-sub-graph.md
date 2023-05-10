# tem 8 How to Fetch Associations via Entity Sub-Graphs

This is quite useful in scenarios involving lazy associations that should be fetched eagerly on a query-basis to avoid 
lazy loading exceptions and N+1 issues. While (LEFT) JOIN FETCH lives inside the query, entity graphs are independent of 
the query. Therefore, the query and entity graphs can be reused (e.g., a query can be used with or without an entity graph, 
while an entity graph can be used with different queries).

Entity graphs are prone to performance penalties as well. Creating big trees of entities (e.g., sub-graphs that have sub-graphs) 
or loading associations (and/or fields) that are not needed will cause performance penalties. 
Think about how easy it is to create Cartesian products of type m x n x p x..., which grow to huge values very fast.

Sub-graphs allow you to build complex entity graphs. Mainly, a sub-graph is an entity graph that is embedded into another
entity graph or entity sub-graph. Let's look at three entities— ``Author``, ``Book``, and ``Publisher``. The Author and Book entities 
are involved in a bidirectional lazy @OneToMany association. The Publisher and Book entities are also involved in a 
bidirectional lazy @OneToMany association.

 * [see the implementation at entity level](../src/main/java/com/drones/dispatcherdrone/bookstore/entities/Author.java)
 * [see the implementation at repo level](../src/main/java/com/drones/dispatcherdrone/bookstore/ropository/AuthorRepository.java)

### Using the Dot Notation (.) in Ad Hoc Entity Graphs

Sub-graphs can be used in ad hoc entity graphs as well. Remember that ad hoc entity graphs allows you to keep the entity 
graph definition at repository-level and not alter the entities with @NamedEntityGraph.
To use sub-graphs, you just chain the needed associations using the dot notation (.), as shown in the following example:
* [see the implementation at repo level](../src/main/java/com/drones/dispatcherdrone/bookstore/ropository/PublisherRepository.java)

### Spring Data Query Mechanism.
```
@Repository
@Transactional(readOnly = true)
public interface PublisherRepository
    extends JpaRepository<Publisher, Long> {

    @EntityGraph(attributePaths = {"books.author"},
                 type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM Publisher p WHERE p.id > 1 AND p.id < 3")
    public List<Publisher> fetchAllIdBetween1And3();
}
```

# tem 9 How to Handle Entity Graphs and Basic Attributes

When Hibernate JPA is around, using entity graphs to fetch only some basic attributes of an entity (not all) requires a 
compromise solution based on:

Enabling Hibernate Bytecode Enhancement
Annotating the basic attributes that should not be part of the entity graph with ``@Basic(fetch = FetchType.LAZY)``
The main drawback consists of the fact that these basic attributes are fetched lazy by all other queries (e.g. findById()) 
and not only by the queries using the entity graph, and most probably, you will not want this behavior. 
So use it carefully!

```
@NamedEntityGraph(
    name = "author-books-graph",
    attributeNodes = {
        @NamedAttributeNode("name"),
        @NamedAttributeNode("books")
    }
)
```

in the repository:

```
@EntityGraph(value = "author-books-graph",
type = EntityGraph.EntityGraphType.LOAD)
public List<Author> findByGenreAndAgeGreaterThan(String genre, int age);
```

By default, attributes are annotated with @Basic, which relies on the default fetch policy. 
The default fetch policy is FetchType.EAGER. Based on this statement, a compromise solution consists of annotating 
the basic attributes that should not be fetched in the fetch graph with ``@Basic(fetch = FetchType.LAZY)`` as here:

```
@Basic(fetch = FetchType.LAZY)
private String genre;
@Basic(fetch = FetchType.LAZY)
private int age;
```
But executing the fetch and load graph again reveals the exactly same queries. This means that the JPA specifications 
don't apply to Hibernate with the basic (@Basic) attributes. Both the fetch graph and the load graph will ignore 
these settings as long as Bytecode Enhancement is not enabled. In Gradle, add the following plug-in:
[hibernate enhancement orm Gradle config](https://github.com/hibernate/hibernate-orm/tree/main/tooling/hibernate-gradle-plugin)

```
Hibernate: 
    select
        a1_0.id,
        b1_0.author_id,
        b1_0.id,
        b1_0.isbn,
        b1_0.publisher_id,
        b1_0.title,
        a1_0.name --> We can see just one field from Author was mapped. 
    from
        author a1_0 
    left join
        book b1_0 
            on a1_0.id=b1_0.author_id 
    where
        a1_0.genre=?
```