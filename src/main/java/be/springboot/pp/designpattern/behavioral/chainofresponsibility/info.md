```
     +----------------+
     |   Handler      |  (Interface)
     +----------------+
         ▲       |
         |       |
 +------------------+
 |  AbstractHandler |  (Implements common behavior)
 +------------------+
        ▲
        |
 ---------------------------------
 |               |               |
 |               |               |
+----------------+ +----------------+ +----------------+
|  AuthHandler   | |  LoggingHandler| |  DataHandler   |  (Concrete Handlers)
+----------------+ +----------------+ +----------------+
         |
         v
   Request Processed or Passed to Next

```