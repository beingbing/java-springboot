# JDBC
- Java DataBase Connectivity
- one of the primary goals of JDBC is to help us abstract out network calls made from application server to DB server.
- open-close principle dictates that we should be open for extension but close for modification.
- our application should be able to extend to new DB vendors, we should not need to change the code if we change vendor.
- we can achieve this by using interfaces
- if there were some common interfaces which was implemented by all the DB vendor, so whichever DB we want to connect with, we just need to provide the configuration details instead of modifying our interaction code.
- our application will be interacting with these common interfaces.
- JDBC is these common interfaces.
- JDBC is a complete package which contain this common interfaces as well.
- JDBC comes with JDK, we do not need to install anything by ourselves.

## Interfaces
- Driver: drives connection using `connect()` from application server to db server
- DriverManager: establishes a connection with DB server using `getConnection()`. Creates object of vendor specific Drivers, and maintains a list of registered drivers.
- ResultSet: functions like a Buffer, if result has too many rows, complete collection is maintained on DB server and a subset is populated in ResultSet.

### Notes:
- every query creates physical resource on DB server as well, Java application has garbage collection but DB do not, so close all connections.