# Why should avoid Uniderectional OneToMany Relationship.


Description: As a rule of thumb, unidirectional @OneToMany association is less efficient than the bidirectional 
@OneToMany or the unidirectional @ManyToOne associations. This application is a sample that exposes the DML statements 
generated for reads, writes and removal operations when the unidirectional @OneToMany mapping is used.

## Key points:

regular unidirectional @OneToMany is less efficient than bidirectional @OneToMany association
using @OrderColumn come with some optimizations for removal operations but is still less efficient than bidirectional 
@OneToMany association
using @JoinColumn eliminates the junction table but is still less efficient than bidirectional @OneToMany association
using Set instead of List or bidirectional @OneToMany with @JoinColumn relationship 
(e.g., @ManyToOne @JoinColumn(name = "author_id", updatable = false, insertable = false)) 
still performs worse than bidirectional @OneToMany association

## Additional:

When using the ``@OneToMany(cascade = CascadeType.ALL, mappedby = "someObject")`` keep in mind only do it when the
number of children is short or fixed somehow. Otherwise it will have performance issues.