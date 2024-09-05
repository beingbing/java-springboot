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

