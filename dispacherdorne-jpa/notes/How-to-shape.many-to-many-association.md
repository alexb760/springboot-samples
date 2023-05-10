# How to shape @ManyToMany relationship.effectively.

Lets imagine a combination of 3 tables ``author, authos_book_list, book`` there is a relationship
many to many.

we could handle effectively by doing the following recommendations:
 ### chooses the owner of the relationship.

Using the default @ManyToMany mapping requires the developer to choose an owner of the relationship and a mappedBy side 
(aka, the inverse side). Only one side can be the owner and the changes are only propagated to the database 
from this particular side. For example, Author can be the owner, while Book adds a mappedBy side.

``@ManyToMany(mappedBy = "books")
private Set<Author> authors = new HashSet<>(); ``

### Always use Set not List.

```
    private Set<Book> books = new HashSet<>();     // in Author
    private Set<Author> authors = new HashSet<>(); // in Book
```
### Avoid CascadeType.ALL and CascadeType.REMOVE
```java
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Book> books = new HashSet<>();
```

### The Join Table.

```
@JoinTable(name = "author_book",
          joinColumns = @JoinColumn(name = "author_id"),
          inverseJoinColumns = @JoinColumn(name = "book_id")
)
```

### Final implementation:

```
// Author...
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "author_book",
              joinColumns = @JoinColumn(name = "author_id"),
              inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();
    
    // books
        @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();
```
