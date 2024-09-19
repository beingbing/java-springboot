# Exceptions

## Introduction
An exception is an event that disrupts the normal flow of a program's execution. Java uses it as an
object to represent an error or unexpected event that occurs during runtime. When you write a method,
you can either deal with the exception or make it the calling method's problem. To make it a caller's
problem, use the `Throwable` class.

## Significance
Without exceptions, we'd relied on coming up with our own construct to manage failure scenarios. One
such approach might be to use error codes. Example -

- `errCode = 0` // (initial value)
- Try to open the file:
  - If the file opens, try to read:
    - If reading fails: `errCode = 2`
  - Then try to close:
    - If closing fails: `errCode = (errCode == 0) ? 3 : errCode | 3`
- If the file doesn’t open: `errCode = 1`

This approach quickly becomes messy (spaghetti code). The business logic gets tightly coupled with
error handling, reducing readability and clarity. With exceptions, business logic is separated from
error handling:
```
try {
    // open the file
    // read the content
    // close the file
} catch (FileOpenException e) {
    // handle file open error
} catch (ReadException e) {
    // handle file read error
} catch (CloseException e) {
    // handle file close error
}
```
But when no handler is found in caller method and an exception occurs, normal execution stops. The
exception traverses the call stack, looking for a handler. If no handler is found, methods get popped
from the stack until `main()` is reached. If `main()` is popped, the JVM terminates the program and
logs the error. This backtracking mechanism is enabled by the `throws` clause, provided by Java Exceptions,
making exception handling more robust and manageable.

## Handling Exception
### Try-Catch Block
Caller method catches and handles the exception by itself.
```
try {
    // Code that might throw an exception
} catch (ExceptionType e) {
    // Handling the exception
} finally {
    // Optional block, executed regardless of exception
}
```
### Throws Clause
Declares that a method can throw an exception, leaving it to the caller to handle.
```java
public void readFile() throws IOException {
    // Code that might throw IOException
}
```
#### Note:
We can transform an exception into another type as well, by handling it in a try-catch block and throwing the
caught exception by wrapping it in another exception.

## Types of Exceptions
Java exceptions are categorized into three main types:
#### 1. Checked Exceptions
The compiler forces the programmer to handle them using try-catch blocks or declaring them using the throws keyword.

## Exception Hierarchy
```
Throwable
├── Error (unrecoverable)
└── Exception (recoverable)
    ├── RuntimeException (unchecked)
    └── [Other Checked Exceptions]
```
- **Throwable:** The superclass of all errors and exceptions.
- **Error:** Represents serious problems that applications should not attempt to handle.
- **Exception:** The superclass of all checked exceptions.
- **RuntimeException:** The superclass of all unchecked exceptions. Thrown by Java runtime due to code bugs.

### Note:
Our goal is to reduce runtime exceptions by turning potential runtime issues into checked exceptions.
This makes it clear that if an exception is thrown, it's due to misuse, not a code error, and the 
program can do nothing to handle the scenario encountered.
