# Stack Data Structure in Java

A **stack** is a fundamental data structure that follows the **Last-In-First-Out (LIFO)** principle, meaning the last element added is the first one to be removed. It's widely used in various computing tasks, such as expression evaluation and backtracking algorithms.

## Stack as a Specialization of `Deque`
Java does not provide a dedicated `Stack` interface in the `java.util` package, but the `Deque` interface can be used to implement a stack since `Deque` allows elements to be added and removed from both ends. Specifically, the `push()` and `pop()` operations of a stack can be implemented using `Deque`.

The **`ArrayDeque`** and **`LinkedList`** classes both implement the `Deque` interface and can be used to create stack-based data structures.

### ArrayDeque as a Stack
`ArrayDeque` is a resizable array-backed implementation of the `Deque` interface. It is faster than `Stack` and `LinkedList` for stack operations and is a better choice when using `Deque` as a stack.

#### Example
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsingArrayDeque {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        // Pop the top element
        System.out.println("Popped: " + stack.pop()); // Output: Popped: 30
        
        // Peek at the top element
        System.out.println("Top: " + stack.peek()); // Output: Top: 20
    }
}
```

- **`push(E e)`**: Adds an element to the top of the stack.
- **`pop()`**: Removes and returns the element from the top of the stack.
- **`peek()`**: Returns the element at the top of the stack without removing it.

### LinkedList as a Stack
`LinkedList` can also be used as a stack by leveraging its `Deque` methods. However, `LinkedList` is typically slower than `ArrayDeque` due to the overhead of maintaining a doubly linked list.

#### Example
```java
import java.util.LinkedList;
import java.util.Deque;

public class StackUsingLinkedList {
    public static void main(String[] args) {
        Deque<String> stack = new LinkedList<>();
        
        // Push elements onto the stack
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        
        // Pop the top element
        System.out.println("Popped: " + stack.pop()); // Output: Popped: Third
        
        // Peek at the top element
        System.out.println("Top: " + stack.peek()); // Output: Top: Second
    }
}
```

Both `ArrayDeque` and `LinkedList` offer efficient stack operations. However, **`ArrayDeque` is the recommended choice for implementing stacks** because it provides better performance and lower memory overhead compared to `LinkedList`.

## Legacy `Stack` Class and Its Usage
The **`Stack` class** is a legacy class that extends the `Vector` class and was part of the original Java 1.0 collections. Although it is still available in Java, it is generally not recommended for modern applications due to its performance limitations and outdated design.

### Key Methods of `Stack` Class
- **`push(E item)`**: Adds an item to the top of the stack.
- **`pop()`**: Removes and returns the item at the top of the stack.
- **`peek()`**: Returns the item at the top without removing it.
- **`empty()`**: Checks if the stack is empty.
- **`search(Object o)`**: Searches for an object in the stack and returns its position.

#### Example
```java
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        
        // Push elements onto the stack
        stack.push(100);
        stack.push(200);
        stack.push(300);
        
        // Peek at the top element
        System.out.println("Top element: " + stack.peek()); // Output: 300
        
        // Pop the top element
        System.out.println("Popped: " + stack.pop()); // Output: 300
        
        // Check if stack is empty
        System.out.println("Is stack empty? " + stack.empty()); // Output: false
    }
}
```

While the `Stack` class works well in simple use cases, modern Java applications should avoid using `Stack` due to the following issues:
- **Synchronization**: `Stack` is a synchronized class, which leads to unnecessary overhead in single-threaded contexts.
- **Inheritance from `Vector`**: `Stack` inherits from `Vector`, which is another synchronized class, making `Stack` slower than alternatives like `ArrayDeque`.

For most stack-related use cases, it is recommended to use **`ArrayDeque`** or **`LinkedList`** instead.

## Applications of Stack

Stacks have several practical applications, including:

### Expression Evaluation
Stacks are widely used to evaluate mathematical expressions, especially when dealing with **infix**, **prefix** (Polish notation), and **postfix** (Reverse Polish notation) expressions.

### Infix to Postfix Conversion
An infix expression is of the form `A + B`. A postfix expression (Reverse Polish Notation) represents the same expression without parentheses, like `A B +`. The conversion from infix to postfix often uses a stack to handle operator precedence and parentheses.

#### Example
```java
import java.util.Stack;

public class InfixToPostfix {
    public static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            // If character is an operand, add it to the result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } 
            // If character is '(', push it to the stack
            else if (c == '(') {
                stack.push(c);
            } 
            // If character is ')', pop and output from the stack until '(' is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();  // Remove '(' from the stack
            } 
            // If character is an operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        String expression = "A+B*(C^D-E)";
        System.out.println("Postfix: " + infixToPostfix(expression));  // Output: AB+CD^E-*
    }
}
```

### Backtracking (e.g., Maze Solving)
Backtracking algorithms often use stacks to remember decision points when exploring possible solutions. One common use case is solving a maze, where a stack is used to store the current path, and if a dead end is reached, the algorithm backtracks to the last decision point.

## Summary of Key Points

| Data Structure       | Description                                          | Key Methods                      |
|----------------------|------------------------------------------------------|-----------------------------------|
| **ArrayDeque (as Stack)** | Resizable array-backed, faster for stack operations | `push()`, `pop()`, `peek()`       |
| **LinkedList (as Stack)** | Implements `Deque`, can be used for stack operations | `push()`, `pop()`, `peek()`       |
| **Legacy `Stack` class**  | Extends `Vector`, synchronized, not recommended    | `push()`, `pop()`, `peek()`, `empty()` |
| **Applications**         | Expression evaluation, backtracking                | -                                 |

## Conclusion
- **Modern Java stacks** should be implemented using **`ArrayDeque`** or **`LinkedList`**, avoiding the legacy `Stack` class.
- Stacks are essential in various algorithms, especially in expression evaluation and backtracking techniques.
- Using `Deque`-based implementations provides flexibility, performance, and thread-safety (when required).
