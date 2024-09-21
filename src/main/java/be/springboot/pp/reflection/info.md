# Reflection

## How Java works

### 1. Compilation of Source Code:
The Java Compiler (`javac`) converts `.java` files into `.class` files. Each `.class` file contains
bytecode for one class or interface. Bytecode is platform independent.

### 2. Launching JVM:
`java` command invokes JVM to start the execution. It takes the name of class containing `main()`
as input. 

### 3. Parse command-line arguments followed by `java` keyword.

### 4. Application Class Path Setup:
Defining class-path where JVM will look for bytecode.

### 5. JVM initialization:
JVM starts its life-cycle by setting up critical parts of JRE. The setup has following steps –

#### Loading Bootstrap classes:
JVM loads core Java classes in memory that are necessary for running any Java application. That
includes basic classes from `java.lang` package (like String, Object, Class, etc.). Loaded by the
`Bootstrap ClassLoader`, which is the lowest level of class loader and loads classes from the
`rt.jar` (runtime library).

#### Initializing memory spaces:

##### Method area:
Stores class-level information like bytecode, runtime constants, and static variables.

##### Heap space:
Stores objects and dynamically allocated memory

##### Stack space:
Stores method invocation frames, local variables, and partial results.

##### Program Counter Registers:
Keeps track of the address of the current instruction.

##### Native Method Stack:
Used by native methods (methods written in non-Java languages).
