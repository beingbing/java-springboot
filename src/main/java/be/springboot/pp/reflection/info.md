# Reflection

## How JVM creates JRE
#### 1. Compilation of Source Code
- The **Java Compiler** (`javac`) compiles `.java` source files into `.class` files.
- Each `.class` file contains **bytecode** for one type of class, which is platform-independent and executable by the Java Virtual Machine (JVM).
#### 2. Launching the JVM
- The `java` command launches the **Java Virtual Machine** (JVM) and specifies the class containing the `main()` method as the entry point.
#### 3. Parsing Command-line Arguments
- The command-line arguments provided after the `java` keyword are passed to the `main()` method as a `String[] args`.
#### 4. Application Class Path Setup
- The **class path** is defined, indicating the locations (directories or `.jar` files) where the JVM will search for bytecode to load.
#### 5. JVM Initialization
JVM starts its life cycle by setting up critical components of JRE. The setup has the following steps:
##### a) Loading Bootstrap Classes
- The JVM first loads core Java classes necessary to run any Java application, such as classes from the `java.lang` package (`String`, `Object`, `Class`, etc.).
- These classes are loaded by the **Bootstrap ClassLoader**, the lowest-level class loader, which loads classes from the runtime library (`rt.jar`).
##### b) Initializing Memory Spaces
- The JVM allocates and initializes the following memory spaces:
  - **Method Area**: Stores class-level information such as bytecode, runtime constants, and static variables.
  - **Heap Space**: Stores objects and dynamically allocated memory.
  - **Stack Space**: Stores method invocation frames, local variables, and intermediate results.
  - **Program Counter (PC) Register**: Keeps track of the current instruction's address for each thread.
  - **Native Method Stack**: Used by methods written in non-Java languages.
#### 6. Loading and Linking Application Classes
After JVM initialization, the process of loading user-defined classes begins:
##### a) Application Class Loading
- The JVM searches the application classpath for `.class` files and loads the user-defined class that contains the `main()` method.
- Loading of a class means, loading its blueprint (structure and attributes) into memory.
- Other classes are loaded **on-demand** when referenced during execution.
- If a class is referenced in `main()` but not yet loaded, the `ClassLoader` locates the `.class` file, loads it into memory, and creates a `Class` class instance to hold the metadata (like class name, fields list, methods present, their access levels etc.) of the user-defined class.
- The `Class` class has private constructors, only JVM can create its objects.
- In a way, the `.class` file is a stringified object of the `Class` class.
##### b) Class Linking
Once a class is loaded, it goes through a linking process that includes the following steps:
- **Verification**: The bytecode is checked for any invalid or malicious code.
- **Preparation**: Memory is allocated for the class’s static variables, which are initialized to their default values.
- **Resolution**: Symbolic references (e.g., method calls and field accesses) are resolved into direct memory references.
##### c) Class Initialization
- Static fields are initialized with their actual values, and static blocks are executed to prepare the class for use.
#### 7. Main Thread Creation and `main()` Invocation
- The JVM creates the **main thread**, which invokes the `main()` method.
- The `String[] args` parameter receives any command-line arguments passed during the program’s execution.
#### 8. Initialization of the Execution Engine
Interprets and/or compiles bytecode into machine-specific code. This engine operates in two modes –
##### a) Interpreter Mode
- Default Mode, The JVM starts by interpreting the bytecode **line-by-line**.
##### b) JIT (Just-In-Time) Compilation
- Over time, the **JIT compiler** identifies frequently executed code blocks (hotspots) and keeps them compiled into machine-specific code for faster execution.
#### 9. Garbage Collector Initialization
- During execution, the **Garbage Collector (GC)** is responsible for managing memory by reclaiming unused objects and freeing heap space.
#### 10. Native Method Support (JNI)
- The JVM supports native methods via the **Java Native Interface (JNI)**, allowing Java to interact with non-Java code, such as OS-specific libraries.
#### 11. Thread Management Initialization
- The JVM manages the lifecycle of both user-defined threads (like the main thread) and **daemon threads** (such as those for garbage collection and JIT compilation).
  - Each thread gets its own memory stack and PC register.
  - **Synchronization** is managed for threads accessing shared resources.
  - The JVM initialises and manages Daemon threads (background tasks).
#### 12. Dynamic Class Initialization and Static Block Execution
When a class referenced by `main()` is loaded, the JVM executes its static initializers and static blocks before the class is used:
- **Static Initializers**: Initializing static variables and executing code inside static blocks before any method (including `main()`) is executed.
- **On-demand Class Loading**: Classes are loaded only when first referenced.
- **Dependency Order**: Dependencies are initialized before the dependent classes.
- **Caching**: Once a class is loaded, it is cached for future use to avoid reloading.
#### 13. Main Thread Execution
- The **main thread** is executing user-defined code, and other threads (daemon or non-daemon) execute in parallel as required by the application.
#### 14. Runtime Execution
During execution, the JVM manages several aspects of the program:
- **Memory Management**: Allocating and deallocating heap and stack space.
- **Thread Management**: Creating, scheduling, and terminating threads.
- **Garbage Collection**: Reclaiming memory used by unused objects.
- **Class Loading**: Loading classes as needed.
- **Exception Handling**: Managing exceptions thrown during execution.
- **JIT Compilation and Runtime Optimization**: JIT compiler continuously optimizes frequently executed bytecode by compiling it into machine code, improving runtime performance.
#### 15. Program Shutdown
When all non-daemon threads terminate, the JVM begins the **shutdown process**:
- **Finalization**: Any `finalize()` methods (if used) are invoked.
- **Daemon Threads Termination**: All remaining daemon threads are stopped.
- **JVM Exit**: The JVM terminates after the program’s execution is complete.

## Conceptualization of Reflection
Building on the JVM's ability to load classes on demand as instances of metaclass (`Class` class) at runtime, Java introduced the Reflection API. This feature allows us to manipulate these metaclass instances and dynamically load more such instances of classes during runtime.
### Key Components
In Java, every user-defined class is associated by an instance of `java.lang.Class` at runtime. Using this `Class` instance, we can:
- Inspect and manipulate user-defined class instances, fields, and methods at runtime.
- Create new instances of classes at runtime, even if constructors are private.
- Access and modify fields and methods, even if they are private.
- Invoke methods dynamically, without knowing their names at compile time.

## Usage
### Accessing metaclass instance
- **using `.getClass()` method:** The `Object` class is a parent of every object in the inheritance hierarchy, it has `.getClass()`, which will return a metaclass instance.
```java
MyClass obj = new MyClass();
Class<?> cls = obj.getClass();
```
- **Using `.class` syntax:**
```java
Class<?> cls = MyClass.class;
```
- **Using `Class.forName()` method:**
```java
Class<?> cls = Class.forName("com.example.MyClass");
```
### Accessing fields of a class
```java
Field field = cls.getDeclaredField("fieldName");
field.setAccessible(true); // Allows access to private fields
field.set(obj, value); // Set the value of the field
```
### Accessing and invoking methods
```java
Method method = cls.getDeclaredMethod("methodName", parameterTypes);
method.setAccessible(true);
method.invoke(obj, args); // Invoke the method on the object
```
### Accessing constructors
```java
Constructor<?> constructor = cls.getConstructor(parameterTypes);
Object obj = constructor.newInstance(args); // Create a new instance
```

## Commonly used Reflection Classes
- `java.lang.Class`: Provides access to metadata about the class (methods, fields, constructors, etc.).
- `java.lang.reflect.Method`: Represents a method, allowing you to inspect and invoke it.
- `java.lang.reflect.Field`: Represents a field, allowing you to inspect and modify it.
- `java.lang.reflect.Constructor`: Represents a constructor, allowing you to instantiate objects dynamically.
- `java.lang.reflect.Modifier`: Provides utility methods to check field, method, or class modifiers (public, private, etc.).

## Disadvantages
- **Performance Overhead**: Reflection is slower compared to direct method calls because of the dynamic nature of the process.
- **Security Restrictions**: Accessing private fields and methods can violate encapsulation, which might be restricted by the Java SecurityManager.
- **Complexity**: Reflection code can be harder to read, debug, and maintain.

## Real-world use-case
- used for dependency injection
- ORM mapping
- JSON serialization
- JSON deserialization
- Dynamic mocking of objects during unit testing
- used for creating debuggers, IDEs, profilers and other tools.
- tremendously used alongside Annotations.
- used in creating dynamic proxies for interfaces
