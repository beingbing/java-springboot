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
- The JVM searches the application class path for `.class` files and loads the user-defined class that contains the `main()` method.
- Other classes are loaded **on-demand** when referenced during execution.
- If a class is referenced in `main()` but not yet loaded, the `ClassLoader` locates the `.class` file, loads it into memory, and creates a `Class` class object to hold the metadata of the user-defined class.
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
