# Exceptions

## Introduction
An exception is an event that disrupts the normal flow of a program's execution. Java uses Exception
Object to represent an error or unexpected event that occurs during runtime. When you write a method,
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

## Types of Exceptions
Java exceptions are categorized into three main types:

### 1. Checked Exceptions
Exceptions that are checked at compile-time. Java prepares our happy path for scenarios which the
compiler is aware might happen if something goes wrong at runtime. The compiler forces the programmer
to handle them using try-catch blocks or declaring them using the `throws` keyword. All of them inherit
the `Exception` class. They are used for recoverable errors, where the programmer is expected to
handle the situation gracefully. Example - `IOException`, `SQLException`, `FileNotFoundException`, etc.

### 2. Unchecked Exceptions
Exceptions that cannot be expected at compile-time. These exceptions are usually due to programming
errors, such as logical mistakes or improper use of an API that usually cannot be recovered gracefully
from and indicate bugs in the code. All of them inherit the `RuntimeException` class. They are logical
unexpected implementation pitfalls that are not fatal to business logic.
Example - `NullPointerException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`, etc.

### 3. Errors
Errors are serious problems that a reasonable application should not try to catch. They are usually
external to the application and indicate serious system-level issues. Errors are typically unrecoverable,
and the JVM should handle them. Example - `OutOfMemoryError`, `StackOverflowError`, `VirtualMachineError`, etc.

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

#### Note:
Java provides checked exceptions to handle potential failures during implementation. However, some
failures may still occur after the process goes live. Our goal is to eliminate runtime exceptions
by identifying and predicting all possible process failures or mishandling, and converting them
into custom checked exceptions. Once runtime failures are eradicated, we can create a clear action
plan to address any reported issues.

## Creating Custom Exceptions
- Checked
```java
public class InvalidUserException extends Exception {
    public InvalidUserException() {}

  public InvalidUserException(String message) {
    super(message);
  }

  public InvalidUserException(Throwable exception) {
    super(exception);
  }

  public InvalidUserException(String message, Throwable exception) {
    super(message, exception);
  }
}
```
- Unchecked
```java
public class InvalidUserRuntimeException extends RuntimeException {
    public InvalidUserRuntimeException(String message) {
        super(message);
    }
}
```
#### Note:
- `message` should be clear, concise and self-explanatory error message.
- usually custom exceptions are created to be a checked exception.

## Handling Exception
### Try-Catch Block
Caller method catches and handles the exception by itself. Ensure catch blocks for exceptions are
ordered from most specific to most general. If a catch block for a generic exception is placed
before a more specific one, the catch blocks of specific exceptions will become unreachable.
```
try {
    // Code that might throw an exception
} catch (ExceptionType1 | ExceptionType2 e) {
    // Handling the exception
} catch (ExceptionType3 e) {
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
### Throw Keyword
explicitly throws an exception for caller to handle
```
if (condition) {
    throw new IllegalArgumentException("Invalid argument");
}
```
#### Note:
- We can transform an exception into another type as well, by handling it in a try-catch block and
throwing the caught exception by wrapping it in another exception.
- `try` block can be followed by `finally` block as well. In this case, `finally` will be run and
then exception will propagate callback stack.
- If thrown exception does not have a `catch` handler then `finally` will run and exception will
propagate through callback stack.
- Before closing a resource in `finally` always check whether that resource object is not-null.
- Nested try-catch always originates out of the need to open multiple resources in succession.
- **BAD:** `finally` throwing exception before opened resources could be closed.
- If Exception A is thrown because Exception B got produced, then B is suppressed and A becomes
primary Exception. Usually happens when exception is thrown in `finally` block. Its a dreaded
situation which led to the creation of `try-with-resource` block.

## Automatic Resource Management (try-with-resource)
```
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"));
      var out = new FileOutputSteam("output.txt")) {
    // Work with the resource
} catch (IOException e) {
    // Handle the exception
}
```
- Introduced in Java 7, this ensures that resources are closed automatically when the try block exits.
- When multiple resources are opened, they are closed in the reverse order.
- try-with-resource don't need a follow-up `catch` or `finally` block
- if a `finally` block is added, it will be run after implicit `finally` block written by compiler
- classes implementing `AutoCloseable` interface can only be used.
- each resource declaration should be separated by `;`
- scope of `try` block ends at `}` before any user-defined `catch/finally` block starts.

#### Note:
- `Closable` throws `IOException` and extends `AutoClosable` which throws `Exception`
- with `try-with-resource` block, If Exception A is thrown while closing resources when Exception B got
thrown. Then B remains the reported Exception and A gets suppressed. Which is what we desire.

## Best practices -
- throw early, catch later.
- In the method where you realize for the first-time that an error can happen. That's the method where you
have maximum information about the error.
- never swallow thrown exception.
- If you can not update method signature for checked exception, pass it wrapping in RuntimeException.
- printStackTrace() gracefully prints the exception trace and move on to the code after catch() block.
- use StackTraceElement[] for customized stack printing instead of logging stack as it is.
