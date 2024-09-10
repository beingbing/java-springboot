# Databases

## Introduction
- for data to be persistent
- we need efficient mechanism to interchange data b/w application and DB
- JDBC provided us interfaces to write vendor independent code
- JDBC abstracted away the networking part
- but working with JDBC, it was necessary for us to know SQL.
- JDBC didn't queries for us based on business logic.

## ORM
- we need something to do away the need of writing SQL query
- ORM: _Object_ **relational** mapping, is based on that concept
- both _Object_ and **relational** are separate paradigms in themselves (like, there is a paradigm shift in C and Java as C is a procedural language and Java is an OOP language)
- _Object_ -> Java, **Relational** -> DB, and mapping means establishing a set of rules on basis of which one can be translated into another
- ORM is a bridge between Java and DB, so Java can talk to DB and can directly get an object of response result.
- by using ORM, we just need to deal with Objects, ORM will write persistence and fetch queries for us, we just need to instruct it accordingly.
- ORM internally uses JDBC
- example, Hibernate

### Drawbacks -
- join-column associations are loaded lazily.
- lazy loading: Object will not be fetched when other associations were fetched, it will be fetched when needed in code.
- ORM does this to maintain performance.
- A polymorphic field of a Class can contain multiple forms of object, but relational DB implementation of that field object can not have a FK reference a different table in different rows. Example, `User` class can contain `Details details` field which is an interface with three implementation `EducationDetails`, `WorkDetails` and `FamilyDetails`, then table `User` translation can not have a column which can reference 3 different tables using FK depending on row. We will need 3 columns `family_details_id`, `word_details_id_` and `education_details_id`.
- Polymorphism capabilities exists in Java paradigm need extra engineering to translate them in Relational DB paradigm.

## JPA
- Java Persistence API
- JPA is for ORM purposes but it is designed and constructed similarly like JDBC
- which mean, it defines a set of interfaces which different ORM vendors implement.
- example of JPA interfaces: Entity, EntityManager.
- example of ORM, Hibernate, has a Session class which implements Entity and another class SessionFactory which implements EntityManager interface.

## Designing a software
- it has two components, LLD and HLD
- LLD includes classes, interfaces, data modelling, etc.
- apart from these two there is another paradigm known as ERD, specific to DataBases
- ERD: Entity Relationship Design
- ERD have table, their relationship and column definition including Primary keys, Foreign keys etc.

## exercise - 
### Design a website of Motivational Quotes
- with a lot of users
- a user can add a quote
- a user can like an existing quote

#### Solution
we will need 3 tables to accomplish above requirements -
1. User Table
- id (Primary Key)
- password
- username
- email

2. Quote Table
- id (Primary Key)
- Title
- Description
- created_at
- author_id (Foreign Key - PK of User Table)

3. Likes Table
- id (Primary Key)
- appreciator_id (Foreign Key - PK of User Table)
- quote_id (Foreign Key - PK of Quote Table)
- created_at

### Design a Bidding platform
- different items listed
- other users bid on it

#### Solution
we will need 3 tables to accomplish above requirements -
1. User Table
- id (Primary Key)
- password
- username
- email

2. Product Table
- id (Primary Key)
- Title
- Description
- purchased_at
- price_expected
- created_at
- owner_id (Foreign Key - PK of User Table)

3. Bid Table
- id (Primary Key)
- bidder_id (Foreign Key - PK of User Table)
- product_id (Foreign Key - PK of Product Table)
- proposed_price
- created_at

##### Note:
Both problem solutions have same design. This approach of creating `Likes/Bid` table is called 'link-table' which keeps record of many-many relationship between two entity tables.

## differences between OO paradigm and relational paradigm
- No Entity representation in Java exists for 'link-table', its information is configured in object of either of the two entities using `JOIN` query
- Usually count of classes is more than count of tables.
- Granularity of entities: Java finely models classes at granular level, with complex objects composed of many small classes supporting nested object, collections and graphs. Like having an address field of type `Address` for a `Customer` class. But DB tables keep information of only one class, with other associated classes linked via `Foreign Key`, example, `Customer` table have a FK address_id for `Address` table or vice-versa.
- Java supports `Inheritance` and `Polymorphism` but relational DBs indirectly support them via table-per-hierarchy/table-per-subclass (single table contains columns representing fields of all child-classes, values missing are left as null, indicating which child-class might have created that entry).
- Java has `.` operator to get complete granular object using just one method call, whereas relational DB has to make a Join query or multiple DB calls.
- Java relies on business logic for data integrity whereas relational DB implements them using table constraints
- Data access operation in Java are designed to work on an object or a field whereas relational DBs work on rows of table.
- an object can be represented by multiple related table due to Normalization to reduce redundancy.
- Objects have identity, meaning two objects can be distinct even if they contain the same data (identity is based on memory location or object references), whereas Rows in a table are considered identical if all their column values match, with identity often tied to primary keys.

## Hibernate
- allows you to map your classes to tables
- creates query on your behalf to send via JDBC over to DB by filling in necessary specific values provided by you.
- hibernate caches generic query strings as creating them everytime to use with JDBC is costly
- other ORM precompute and cache some queries depending on information gather from your entity table translation.
- uses reflection for translation, using which value is directly update on a variable, no getters/setters are used.
- But if we want Hibernate to use getters/setters while doing translation then set `@Id` and `@GeneratedValue` on `getId()` instead of setting them directly on `id`.
- in short, if we annotate fields, reflection will be done on them and if we annotate getters/setters, reflection will be done on them instead.
- if reflection is set on getters/setters then dirty-checks and real value injection into SQL query templates will also happen via them.
- use `@Column(name = "column_name")` for informing Hibernate about mapping if field name and column names are different.
- if you want Hibernate to do basic calculations like currency conversion, taking average, finding max/min on your behalf.
  - Use `@ColumnTransformer()` and specify read/write transformations.
  - Use `@Formula()` and specify transformation formula

## JPQL (Java Persistence Query Language)
- it's a part of JPA
- Spring Data provides multiple ways to create and execute a query, and JPQL is one of these
- Spring Data defines queries using the @Query annotation in Spring to execute both JPQL and native SQL queries.
- JPQL is default approach to create and execute query

## Primary Key
- Natural PK, denoted by id and simple to guess, example, User table has user_id. It has physical significance of giving a user a unique identity.
- Composite PK: combination of columns is PK. When a combination is unique but no single column in itself is unique for each row.
- Surrogate PK: like Natural PK, but lacks in physical significance. When you can not create a composite PK and Natural PK. example, Likes table id or Bid table id in above exercise.

## Spring Data JPA
- wrapper of Hibernate
- Spring Data is a library of Spring like spring-mvc which deals with data persistence
- spring-data-jpa is a specific implementation of it using Hibernate which uses JPA repositories for data persistence
- interface `JpaRespository` has two generics <T, ID> where T represents data-type of entity on which it will function and ID is data-type of PK
- we create interfaces extending `JpaRepository` and declare our named-queries as function in it.
- Spring writes implementation/definitions of your declared functions written in interfaces extended from `JpaRepository`
- when extended interface is annotated by `@Repository`, Spring creates a Bean of them.
- that Bean is called a proxy class whose name starts with `$`.
- proxy is a concept of advanced reflection, which functions as a broker between two parties.
- while creating a proxy class out of interface, implementation is written for declared functions by Spring dynamically.
- Documentation of `JpaRepository` has conventions explained for formats to declare desired functions in extended interfaces.
- query is abstractly written for you.
- to write complicated queries which can not be declared as function names, we can write JPQL/SQL using `@Query()`
- `Proxy` class is a part of reflection package, inside it we have `newProxyInstance()`. Spring-data calls it to create proxy instance of interfaces.
