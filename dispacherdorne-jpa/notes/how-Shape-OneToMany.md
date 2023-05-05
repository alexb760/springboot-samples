# How to Effectively Shape the @OneToMany Association

The bidirectional @OneToMany association is probably the most encountered association in our Domain Model. 
Based on this statement, this book takes advantage of this association in a significant number of examples.

Lets consider two entities ``PrescriptionLoad`` and ``PrescriptionMedicationItem`` which a ``PrescriptionLoad``
has many ``PrescriptionMedicationitem`` that means a @OneToMany associations.

## Best practices:

### Always Cascade from Parent-Side to Child-Side:
Cascading from child-side to parent-side is a code smell and bad practice and it is a clear signal that it is 
time to review your Domain Model and application design.

``@OneToMany(cascade = CascadeType.ALL)``

### Don't Forget to Set mappedBy on the Parent-Side:
The mappedBy attribute characterizes a bidirectional association and must be set on the parent-side. 
In other words, for a bidirectional @OneToMany association, 
set mappedBy to @OneToMany on the parent-side and add @ManyToOne on the child-side referenced by mappedBy. 
Via mappedBy, the bidirectional @OneToMany association signals that it mirrors the 
@ManyToOne child-side mapping. In this case, we add in Author entity to the following:

``@OneToMany(cascade = CascadeType.ALL, mappedBy = "prescriptionLoad")``

### Set orphanRemoval on the Parent-Side:
Setting orphanRemoval on the parent-side guarantees the removal of children without references. 
In other words, orphanRemoval is good for cleaning up dependent objects that should not exist without a reference from an owner object. 
In this case, we add orphanRemoval to the Author entity:

``@OneToMany(cascade = CascadeType.ALL, mappedBy = "prescriptionLoad", orphanRemoval = true)``

### Keep Both Sides of the Association in Sync:
You can easily keep both sides of the association in sync via helper methods added to the parent-side. 
Commonly, the addChild(), removeChild(), and removeChildren() methods will do the job pretty well.
take a look at [PrescriptionLoad class](../src/main/java/com/drones/dispatcherdrone/medicationmanager/ports/entity/PrescriptionLoad.java)

### Override equals() and hashCode()

## Use Lazy Fetching on Both Sides of the Association:
By default, fetching a parent-side entity will not fetch the children entities. This means that @OneToMany is set to lazy. 
On the other hand, fetching a child entity will eagerly fetch its parent-side entity by default. 
It is advisable to explicitly set @ManyToOne to lazy and rely on eager fetching only on a query-basis.
In this case, the Book entity explicitly maps the @ManyToOne as LAZY:

### Pay Attention to How toString() Is Overridden:
If toString() needs to be overridden, then be sure to involve only the basic attributes fetched when the entity is loaded 
from the database. Involving lazy attributes or associations will trigger separate SQL statements 
that fetch the corresponding data or throw LazyInitializationException. For example, if we implement the toString() method 
for Author entity then we don't mention the books collection, we mention only the basic attributes (id, name, age and genre):

### Use @JoinColumn to Specify the Join Column Name

The join column defined by the owner entity (Book) stores the ID value and has a foreign key to the Author entity. 
It is advisable to specify the desired name for this column. This way, you avoid potential confusions/mistakes 
when referring to it (e.g., in native queries). In this case, we add @JoinColumn to the Book entity as follows:
@JoinColumn(name = "load_id")

https://github.com/Apress/spring-boot-persistence-best-practices