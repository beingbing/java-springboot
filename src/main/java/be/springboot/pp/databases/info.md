# Databases

## Introduction
- for data to be persistent
- we need efficient mechanism to interchange data b/w application and DB
- JDBC provided us interfaces to write vendor independent code
- JDBC abstracted away the networking part
- but working with JDBC, it was necessary for us to know SQL.
- JDBC didn't query for us based on business logic.

## ORM
- we need something to do away the need of writing SQL query
- ORM: _Object_ **relational** mapping, is based on that concept
- both _Object_ and **relational** are separate paradigms in themselves (like, there is a paradigm shift in C and Java as C is a procedural language and Java is an OOP language)
- _Object_ -> Java, **Relational** -> DB, and mapping means establishing a set of rules on basis of which one can be translated into another
- ORM is a bridge between Java and DB, so Java can talk to DB and can directly get an object of response result.
- by using ORM, we just need to deal with Objects, ORM will write persistence and fetch queries for us, we just need to instruct it accordingly.
- ORM internally uses JDBC
- whenever we use an ORM, Then we have to manage persistence unit
- persistence unit is responsible for everything we do with the database
- persistence unit is the owner of database connectivity and talking to DB. Without it no interaction is possible.
- example, Hibernate

### Drawbacks -
- object composition/associations when called upon using '.' operator then associated join-column query are done lazily.
- lazy loading: Object will not be fetched when other associations were fetched, it will be fetched when needed in code.
- ORM does this to maintain performance.
- A polymorphic field of a Class can contain multiple forms of object, but relational DB implementation of that field object can not have a FK reference of a different table in different rows. Example, `User` class can contain `Details details` field which is an interface with three implementation `EducationDetails`, `WorkDetails` and `FamilyDetails`, then table `User` translation can not have a column which can reference 3 different tables using FK depending on row. We will need 3 columns `family_details_id`, `word_details_id_` and `education_details_id`.
- Polymorphism capabilities exist in Java paradigm need extra engineering to translate them in Relational DB paradigm.

## JPA
- Java Persistence API
- It is a standard collection of interfaces
- JPA says, if you are an ORM provider then you have to implement all interfaces defined in me
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

#### Note
whenever thinking of creating tables, the design should be such that it supports CRUD efficiently

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

3. Likes Table (these kind of tables are called link tables)
- id (Primary Key)
- appreciator_id (Foreign Key - PK of User Table) (many to many)
- quote_id (Foreign Key - PK of Quote Table)      (many to many)
- created_at

### Design a Bidding platform (OLX)
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
Both problem solutions have same design. This approach of creating `Likes/Bid` table is called 'link-table' which keeps record of many-many relationship between two entity tables and reduce redundancy. This is a kind of normalisation.

## HW
- revise SQL.
- learn normalisation.

## differences between OO paradigm and relational paradigm
- No Entity representation in Java exists for 'link-table', its information is configured in object of either of the two entities using `JOIN` query
- Usually count of classes is more than count of tables. Because, it is recommended to create small class and use composition due to single responsibility principle, and other such rules. Example, if our architecture is such that a User will have only one address, then we can keep an address column in user table, there is not point of creating a separate address table and use Join Query every time a user needs to be fetched due to foreign key. When we surely know that every time join key will only get one result. But in Java world, it makes a lot of sense to keep user and address as separate classes (irrespective of how data is kept in DB), and use composition to relate them. In this way if some features only require to have address details, so we can pass only address object and keep that feature decoupled (or loosely coupled) with user details.
- The above explained gap between designing a relation among objects and designing a relational DB is called problem of granularity.
- Granularity of entities: Java finely models classes at granular level, with complex objects composed of many small classes supporting nested object, collections of objects and graphs interaction.
- Java supports `Inheritance` and `Polymorphism` but relational DBs indirectly support them via table-per-hierarchy/table-per-subclass (single table contains columns representing fields of all child-classes, values missing are left as null, indicating which child-class might have created that entry, example KYC documents).
- Talking about associations/composition, one simple getter() call can be multiple DB calls or join queries on DB level.
- Java has `.` operator to get complete granular object using just one method call, whereas relational DB has to make a Join query or multiple DB calls.
- Problem of Identity: To define a unique row in DB table, we only need a PK. and if two queries gave row with same PK then we can easily say that both queries give identical results. But object's identity, meaning two objects can be distinct even if they contain the same data (identity is based on hashcode, memory location or object references), whereas Rows in a table are considered identical if all their column values match, with identity often tied to primary keys.
- Java relies on business logic for data integrity whereas relational DB implements them using table constraints
- Data access operation in Java are designed to work on an object or a field whereas relational DBs work on rows of table.
- an object can be represented by multiple related table due to Normalization to reduce redundancy.

## Hibernate
- Hibernate is an implementation of JPA
- for persistence unit we are using: `HibernatePersistenceProvider`
- allows you to map your classes to tables
- creates query on your behalf to send via JDBC over to DB by filling in necessary specific values provided by you.
- hibernate caches generic query strings as creating them everytime to use with JDBC is costly
- other ORM precompute and cache some queries depending on information gather from scanning your entity while table translation.
- uses reflection for translation, using which value is directly update on a variable, no getters/setters are used.
- But if we want Hibernate to use getters/setters while doing translation then set `@Id` and `@GeneratedValue` on `getId()` instead of setting them directly on `id`.
- in short, if we annotate fields, reflection will be done on them and if we annotate getters/setters, reflection will be done on them instead.
- if reflection is set on getters/setters then dirty-checks and real value injection into SQL query templates will also happen via them.
- use `@Column(name = "column_name")` for informing Hibernate about mapping if field name and column names are different.
- if you want Hibernate to do basic calculations like currency conversion, taking average, finding max/min on your behalf.
  - Use `@ColumnTransformer()` and specify read/write transformations.
  - Use `@Formula()` and specify transformation formula
- to add Hibernate as dependency -
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>	<!-- it automatically imports Hibernate which contains JDBC in it -->
</dependency>
```
for some customization -
```properties
# JPA/Hibernate Configuration
## persistence-unit properties declaration [name: default]
# other option is to write 'create', but create will drop existing
# table and add new table with only current entries hence we used
# 'update' as it will first look for an existing table.
# 'create' is mostly used while writing unit tests in which we need
# to start a mock DB. so, 'create' for testing and 'update' for real-world
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect // remove the property setting and it will be selected by default
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.auto_quote_keyword=true
spring.jpa.properties.hibernate.use_sql_comments=true
```
for connecting to database (as we did in JDBC) -
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/practice?useSSL=false
spring.datasource.username=root
spring.datasource.password=bharatpenew
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### @Entity
- it signals Hibernate that it is supposed to be a table
- maps class and table if exit. Create if table does not exist. This create if not present order comes from ddl-auto (also, hbm2ddl)
- 

### @Table
- to specify table name if entity and table names are different

### @Id
- to mark a field as PK, if not done then hibernate won't be translate.

### @GeneratedValue
- value to key will automatically generated by Hibernate at DB

### EntityManager
- interfaces exist in JPA

### EntityManagerFactory
- interfaces exist in JPA

### @Enumerated

### @Immutable
- tables which you want to keep read only

### @Column
#### updatable
- to place immutability on individual column
- can make DDL restriction as well

#### nullable
- to restrict to get null value
- can make DDL restriction as well

### @Generated
- not provided by developer, automatically generated by system
- have optinons for generate value - NEVER, INSERT, UPDATE, ALWAYS
- never means, one generated, it will never be regenerated
- insert means, generated at the time of insertion only
- update means, generated at the time of modification only
- always mean, generate with every action
- used in conjunction with `@Temporal(TemporalType.TIMESTAMP)`

### @Embedded - @Embeddable
- for composition
- splitting and making two classes out of one table

### @AttributeOverrides - @AttributeOverride
- to override attributes

### @OneToMany / @OneToOne
#### orphanRemoval
#### cascade
- all:
- persist:
- merge:
- refresh:
- if we miss it in association, it is possible that an object is created but not persisted and whose PK is referenced in persisting another object. In this case code will break.
#### mappedBy
- who contains FK is the owner in case of one-to-many/many-to-one
#### fetch

### @OneToOne
#### optional
- true:
- false:

### @ManyToMany
- anyone side can be the owner

## associations in table
- unidirectional (only o1 -> o2 possible) and bidirectional (both o1 -> o2 and o2 -> o1 possible) association
- unidirectional 1:1 and bidirectional 1:1 implementation
- in unidirectional, if we delete o1, we can instruct to delete o2 as well, but for vice-versa we will need bidirectional association.
- `LibraryMembership` -> `User` is unidirectional. rows of `LibraryMembership` can be deleted easily, but for deleting `User` you need to remove all FK references.
- `User` <-> `Account` is bidirectional but FK is owned by `User`. rows in `Account` can be deleted easily, but for deleting `User` you need to remove all FK references.
- 

## JPQL (Java Persistence Query Language)
- it's a part of JPA
- Spring Data provides multiple ways to create and execute a query, and JPQL is one of these
- Spring Data defines queries using the @Query annotation in Spring to execute both JPQL and native SQL queries.
- JPQL is default approach to create and execute query

## Dirty checking
- hibernate once loads results and converts them into objects
- tracks those object to see for any changes
- when transaction is commited then changes are analyzed and updates are done if found

## Primary Key
- Natural PK, denoted by id and simple to guess, example, User table has user_id. It has physical significance of giving a user a unique identity.
- Composite PK: combination of columns is PK. When a combination is unique but no single column in itself is unique for each row.
- Surrogate PK: like Natural PK, but lacks in physical significance. When you can not create a composite PK and Natural PK. example, Likes table id or Bid table id in above exercise.

we talked till now 
- JDBC -> JDBC implementation clients -> ORM -> JPA (JPQL) -> Hibernate
- now, above it comes, Spring Data JPA

## Spring Data JPA
- wrapper of Hibernate
- spring-data-jpa is a library of Spring like spring-mvc which deals with data persistence
- spring-data-jpa is a specific implementation for data persistence using Hibernate which uses JPA repositories for data persistence instead of using entity-manager.
- spring-data-jpa abstracts out entity-manager usage into repositories
- interface `JpaRepository` is that abstraction
- to include it in project -
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>	<!-- it automatically imports Hibernate which contains JDBC in it-->
</dependency>
```
- interface `JpaRespository` has two generics <T, ID> where T represents data-type of entity on which it will function and ID is data-type of PK
- we create interfaces extending `JpaRepository` and declare our named-queries as function in it.
- Spring writes implementation/definitions of your declared functions written in interfaces extended from `JpaRepository`
- when extended interface is annotated by `@Repository`, Spring creates a Bean of them.
- that Bean is of a proxy class whose name starts with `$`, followed by 'Proxy' keyword, which is followed by an integer index maintained by spring-data-jpa.
- proxy is a concept of advanced reflection, which functions as a broker between two parties.
- while being a bridge of communication between client and server, proxy can do a lot of things to the stuff it is conveying from client to server.
- so, proxy can be thought of like interceptors/filters.
- while creating a proxy class out of interface in runtime, implementation is written for declared functions reflectively and naming is done by spring-data-jpa dynamically.
- Documentation of `JpaRepository` has conventions explained for formats to declare desired functions in extended interfaces.
- query is abstractly written for you.
- method name should match with column name of entity field.
- to write complicated queries which can not be declared as function names, we can write JPQL/SQL using `@Query()`

### proxy class
- `Proxy` class is a part of reflection package, inside it we have `newProxyInstance()`. Spring-data calls it to create proxy instance of interfaces at runtime.
- to call `newProxyInstance()`, we need to pass `ClassLoader`, we can get that by doing `.getClassLoader()` on a class instance.
- `ClassLoader` is the entity responsible for loading classes by reading the bytecode.
- and, we need to pass the set of interfaces whose implementation we want.
- example, we need to write a class, which implements 5 different interfaces, then those interfaces will comprise the list.
- last, we need to pass `InvocationHandler`, which is also an interface. It has `invoke()`, which does all the work. 
- profiler softwares, profiles our classes and functions by recording how much time each take to run.
- it also gives hollistic performance review of time and memory footprint of a software.
- these softwares are written using proxy-reflection. The way they work is -
  - for a class to profile, a subclass of it is created with all the profiling abilities added on to it.
  - and a software is written implementing and creating a new subclass for the whole software which needs to be tested.
  - this kind of engineering is called, separation of concern, where the concern to test the classes is implemented separately in a profiling software.
  - this separation of concern is made possible by proxy class.
  - check `ProfilingService` for example.
- To tie our `ProfilingService` example with how spring-data-jpa does the same thing -
  - When we write a `@repository` interface by extending `JpaRepository`, and declare some methods.
  - spring-data-jpa internally invokes `Proxy` class to create an instance.
  - The `InvocationHandler` does add the implementation of declared methods by analyzing the entity and decyphering the declared method into a JPQL based on the entity and then it just calls the target class which `InvocationHandler` is invoked with.
  - in this process, it is also possible that a chain of proxy classes are involved by invoking a proxy class over another proxy class.
  - the target class is some pre-written implementation of `JpaRespository` maybe by the name `SimpleJpaRepository`.
  - the proxy chaining process is called Decorator Pattern.

#### Internal working
The `Proxy` class in Java provides a mechanism to create dynamic proxy instances at runtime. The core of how the `Proxy` class works revolves around the `java.lang.reflect.Proxy` class and `InvocationHandler` interface. Dynamic proxies allow you to intercept method calls on objects, which is useful for a variety of use cases like logging, profiling, security checks, etc.

Let’s break down the internal working of the `Proxy` class:
1. **Proxy Class Generation**:
  - When you call `Proxy.newProxyInstance()`, a new class is generated at runtime. This class implements the interface(s) specified in the proxy creation method and delegates the method calls to an `InvocationHandler`.
  - The proxy class that gets generated dynamically is created as a subclass of `java.lang.reflect.Proxy`. This class implements the methods of the provided interfaces but does not contain any real business logic. Instead, it forwards all method calls to an `InvocationHandler`.

2. **Proxy.newProxyInstance() Method**:
   The `Proxy.newProxyInstance()` method is the entry point for creating a dynamic proxy instance. The method signature is:

   ```java
   public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
       throws IllegalArgumentException;
   ```

   Here’s how this method works:
  - **ClassLoader (`loader`)**: This is the class loader that defines where to load the proxy class from. Typically, this will be the class loader of the interface(s) you're proxying.
  - **Interfaces (`interfaces`)**: An array of `Class` objects representing the interfaces that the proxy class will implement.
  - **InvocationHandler (`h`)**: This is the core component that handles method calls. It implements the `invoke()` method, which is called every time a method on the proxy is invoked.

3. **InvocationHandler’s Role**:
  - When a method is invoked on the proxy object, the `InvocationHandler`’s `invoke()` method is called. The signature of this method is:

    ```java
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
    ```

  - **Proxy**: The proxy instance on which the method was called.
  - **Method**: The `Method` object corresponding to the method that was invoked.
  - **Args**: The arguments passed to the method. If the method takes no arguments, this will be `null`.

   The `InvocationHandler` then processes the method call (e.g., logging, profiling, or forwarding the call to the target object) and returns a result (or throws an exception, if applicable).

4. **Proxy Class Lifecycle**:
  - When the proxy class is first created, it uses the provided class loader to generate the proxy class. It does this by using a library like `javassist` or `ASM` behind the scenes to generate bytecode that implements the required interfaces.
  - The proxy class is cached, so subsequent proxy creation for the same interface will reuse the already generated proxy class.

5. **Method Dispatching**:
  - When you invoke a method on the proxy object, internally the method call is dispatched to the `InvocationHandler`. It looks up the `Method` object corresponding to the invoked method, then calls the `InvocationHandler.invoke()` method.
  - The `InvocationHandler.invoke()` method is responsible for deciding whether to pass the method call to the actual target object (if any) or perform some other action (e.g., logging, caching, security checks).

6. **Performance Considerations**:
  - Dynamic proxy creation is done via bytecode generation, which involves some overhead. However, the proxy class is generated only once, and after that, method dispatch is relatively fast.
  - The use of reflection (for looking up methods) may incur a slight performance cost, but this is usually acceptable unless the proxy is in a performance-critical path.

### Example: A Deeper Look into `Proxy.newProxyInstance()`

Here’s a basic structure of how the `Proxy` class might behave under the hood.

```java
// Proxy.newProxyInstance invocation example
Service proxy = (Service) Proxy.newProxyInstance(
    Service.class.getClassLoader(),
    new Class[] { Service.class },
    new MyInvocationHandler(new ServiceImpl())
);

// Internally, Proxy does the following:
/**
1. Generate a proxy class at runtime, let's call it "Proxy$0".
   - This class will implement the `Service` interface.
   - All the methods in the `Service` interface (like `performTask()`) will be implemented in the proxy class.
   
2. The generated proxy class will look something like this (simplified):
*/

   class Proxy$0 implements Service {
       private final InvocationHandler handler;

       Proxy$0(InvocationHandler handler) {
           this.handler = handler;
       }

       @Override
       public void performTask() {
           try {
               // Calls the InvocationHandler's invoke method
               handler.invoke(this, Service.class.getMethod("performTask"), null);
           } catch (Throwable t) {
               throw new RuntimeException(t);
           }
       }

       @Override
       public int calculate(int x, int y) {
           try {
               // Calls the InvocationHandler's invoke method with the method and parameters
               return (int) handler.invoke(this, Service.class.getMethod("calculate", int.class, int.class), new Object[]{x, y});
           } catch (Throwable t) {
               throw new RuntimeException(t);
           }
       }
   }

// 3. Once the proxy class is created, the method calls are routed to the `InvocationHandler`, which determines how to handle the method calls.
```

### Key Concepts:
- **InvocationHandler**: Central in handling all method calls on the proxy.
- **Proxy Class**: Dynamically generated class implementing the specified interface(s).
- **Method Reflection**: Used to invoke methods on the target object or perform custom logic.

### Important Points:
- The proxy class does not contain the actual implementation of the methods. Instead, it forwards the method calls to the `InvocationHandler`.
- The `InvocationHandler` has complete control over how to handle the method calls. It could delegate the call to a real object, modify the behavior, or do something entirely different (like logging or profiling).

This is how the `Proxy` class in Java dynamically intercepts method calls and allows custom behavior to be applied through `InvocationHandler`.

### findById()
- it returns an Optional
- because id might not exist
- so intead of returning null, it returns an empty optional
