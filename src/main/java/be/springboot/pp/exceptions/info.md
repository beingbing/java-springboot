# Exceptions

## Introduction
An exception is an event that disrupts the normal flow of a program's execution. Java uses it as an
object to represent an error or unexpected event that occurs during runtime. When you write a method,
you can either deal with the exception or make it the calling method's problem. To make it a caller's
problem, use the `Throwable` class.

## Handling Exception
### Try-Catch Block
Caller method catches and handles the exception by itself.
```java
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

