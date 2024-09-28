# JDBC
JDBC (Java Database Connectivity) is an API provided by Java to enable applications to interact with databases. It comes packaged with the JDK, hence, no need to install it separately. One of the primary goals of JDBC is to abstract away the complexity of making network calls from the application server to the database server, providing a common interface to interact with different database vendors. To add JDBC as dependency -
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.0.0</version>
</dependency>

<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jdbc</artifactId>
</dependency>
```
and to connect with databases -
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/practice?useSSL=false
spring.datasource.username=root
spring.datasource.password=bharatpenew
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## How JDBC Works
JDBC serves as a bridge between the application and the database. By using interfaces, JDBC provides a standard way to interact with any type of database. It provides common interfaces that all database vendors implement. This way, your application interacts with these standard interfaces, while the underlying database drivers take care of the specifics of communicating with the actual database server. When switching from one database vendor (e.g., MySQL, PostgreSQL, Oracle) to another, you only need to change the configuration (like the database URL, username, password, etc.). The core interaction logic with the database remains untouched.

### Open-Closed Principle
In line with the object-oriented principle of being "open for extension but closed for modification," JDBC allows extending support for new database vendors without changing the underlying interaction code.

## Key JDBC Interfaces
### Connection
If a `Connection` is closed, all the `Statement` instances created from it will get automatically closed, which will close `ResultSet` attached to those `Statement` instances as well. So, if a parent is closed, all its children will get automatically closed.

### Driver
The Driver interface is responsible for establishing a connection between the application (client) and the database server. It does so using the `connect()` method. Database vendors provide their own implementations of the Driver interface to handle their specific logic, which we need to install as a client to get interaction ability.

### DriverManager
DriverManager is responsible for managing a list of database drivers and establishing a connection to the database using the `getConnection()` method. It maintains a list of registered database drivers (each specific to a database vendor), and when a connection is requested, it iterates over the list and selects the appropriate driver to use based on the provided connection details (URL, username, password, etc.).

### Statement
Every execution of statement query initializes physical resources on DB server as well.

#### getMaxRows()

#### execute()

#### executeQuery()

#### getMoreResults()

### ResultSet
The ResultSet interface is used to represent the results returned by a query. It functions like a memory buffer for the results of a database query. If a query returns too many rows, the ResultSet manages a subset of them, with the rest remaining on the database server, tracked by a DB cursor (physical resource). Once memory subset of a query result is exhausted, next `next()` call will internally fetch fresh batch of result subset and DB cursor will move forward on next fresh batch. By default, only one `ResultSet` per `Statement` object can be open at a time. If you need multiple `ResultSet` objects, they must be created by separate `Statement` objects.  If you attempt to execute a new query on the same `Statement`, the previous `ResultSet` will be automatically closed.

#### next() pointer and DB cursors
- at first, pointer is placed before first row
- with every call of next(), pointer moves one row ahead

#### ResultSet Types
- `SENSITIVE`: A sensitive `ResultSet` reflects changes made to the underlying database while the `ResultSet` is open. If rows are added, updated, or deleted in the database, those changes are visible in the `ResultSet`.
- `INSENSITIVE`: An insensitive `ResultSet` is not affected by changes made to the database after the query is executed. It works with a snapshot of the data at the time the query was run.
- `TYPE_FORWARD_ONLY`
- `TYPE_SCROLL_INSENSITIVE`
- `TYPE_SCROLL_SENSITIVE`

#### ResultSet Concurrency Modes
- `CONCUR_READ_ONLY`: You can only read from the `ResultSet` and cannot update the data while iterating through it.
- `CONCUR_UPDATABLE`: You can update the data in the `ResultSet`, and those updates are sent to the database in batches (not immediately after each update), optimizing performance.

#### first()

#### last()

#### absolute()

#### moveToInsertRow()
- new row will be added at the end (just like an array)
- current pointer position will be remembered while insertion at the end is happening.

## Managing Database Connections
When interacting with a database, every query creates physical resources on the database server. While Java has automatic garbage collection for memory management, databases do not have an automatic cleanup mechanism for open connections or results. Therefore, it's important to close database connections, `ResultSet`, and `Statement` objects to avoid resource leaks or exhaustion. It's important to follow these best practices to optimize performance.

## Connection Pooling
Re-establishing a database connection for every query can be expensive in terms of time and resources. To improve performance, applications often use connection pools. A connection pool works similarly to a thread pool. Instead of creating a new connection for every query, a pool of connections is maintained, and connections are reused. Popular servers like Tomcat offers JDBC connection pools that can be used in Java applications. Internally, a connection pool works like a semaphore of connection objects. It ensures that no two threads pick the same connection simultaneously, which would cause a race condition. The semaphore ensures thread-safe access to the pool.

## exercise
practice using `ResultSet` `updateRow()`, `moveToInsertRow()`, `moveToCurrentRow()`, `insertRow()`, `PreparedStatement()`
