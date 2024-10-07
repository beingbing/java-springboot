# Queue Interface
The `Queue` interface is part of the `java.util` package and represents a collection designed to hold elements before processing, following the **FIFO** principle. It is an ordered collection where elements are inserted at the end and retrieved from the front.

## Characteristics of a `Queue`
- **FIFO Order**: The element inserted first is removed first.
- **Null Handling**: Most implementations of `Queue` do not allow `null` elements.
- **Common Methods**:
    - `offer(E e)`: Inserts an element into the queue, returning `true` if successful or `false` if the queue is full.
    - `poll()`: Retrieves and removes the head of the queue, returning `null` if the queue is empty.
    - `peek()`: Retrieves, but does not remove, the head of the queue, returning `null` if the queue is empty.
    - `remove()`: Removes the head of the queue, throwing an exception if the queue is empty.

## Implementations: PriorityQueue
The `PriorityQueue` is a special kind of queue where elements are ordered based on their priority rather than their insertion order. The elements are ordered either according to their natural ordering or by a `Comparator` provided at queue creation.

### Key characteristics of `PriorityQueue`
- **Priority-based ordering**: The head of the queue is the least element, according to the specified ordering.
- **Non-FIFO behavior**: Unlike a regular queue, the `PriorityQueue` does not guarantee FIFO behavior.
- **Not thread-safe**: `PriorityQueue` is not synchronized and should be used with proper synchronization in concurrent environments.
- **Null Handling**: `PriorityQueue` does not allow `null` elements.
- **Common Methods**:
  - `offer(E e)`: Inserts the element into the priority queue, ordering it by priority.
  - `poll()`: Retrieves and removes the head of the queue (the element with the highest priority).

### Example
```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        priorityQueue.offer(20);
        priorityQueue.offer(10);
        priorityQueue.offer(30);
        
        System.out.println("PriorityQueue: " + priorityQueue);  // Output: [10, 20, 30]
        
        System.out.println("Polling: " + priorityQueue.poll()); // Removes and returns the head (10)
        System.out.println("After polling: " + priorityQueue);  // Output: [20, 30]
    }
}
```

# Deque Interface
The `Deque` (double-ended queue) interface extends `Queue` and allows elements to be inserted or removed from both ends of the queue. It can function as both a queue (FIFO) and a stack (LIFO).

## Key characteristics of a `Deque`
- **Double-ended**: Elements can be added and removed from both the head and the tail.
- **Null Handling**: Most implementations do not permit `null` elements.
- **Common Methods**:
    - `addFirst(E e)`, `addLast(E e)`: Insert elements at the front or the end of the deque.
    - `removeFirst()`, `removeLast()`: Remove elements from the front or the end.
    - `getFirst()`, `getLast()`: Retrieve, but do not remove, the first or last element.

## Common Deque Implementations
- **ArrayDeque**: A resizable array implementation of the `Deque` interface. It is faster than `LinkedList` for both stack and queue operations and has no capacity restrictions.
- **LinkedList**: Also implements `Deque`, allowing operations on both ends of the list.

# BlockingQueue Interface
The `BlockingQueue` interface extends `Queue` and represents a thread-safe queue that supports operations that wait for the queue to become non-empty when retrieving elements and to become non-full when adding elements.

## Key characteristics of `BlockingQueue`
- **Thread-safety**: All operations are thread-safe.
- **Blocking operations**: `put()` and `take()` block until the queue is ready for insertion or retrieval.
- **Bounded and unbounded queues**: `BlockingQueue` can have a fixed capacity (bounded) or no capacity limits (unbounded).
- **Common Methods**:
    - `put(E e)`: Inserts the specified element into the queue, waiting if necessary.
    - `take()`: Retrieves and removes the head of the queue, waiting if necessary until an element becomes available.

## Common `BlockingQueue` Implementations
### ArrayBlockingQueue Class
`ArrayBlockingQueue` is a bounded, blocking queue backed by an array. It is thread-safe and is often used when you need a queue with a fixed capacity that blocks when the queue is full or empty.

#### Key characteristics of `ArrayBlockingQueue`
- **Fixed size**: The capacity of the queue is specified at the time of creation and cannot be changed.
- **Thread-safe**: All operations are thread-safe and use locks to ensure safe access from multiple threads.
- **Blocking operations**: The `put()` method blocks when the queue is full, and the `take()` method blocks when the queue is empty.

#### Example
```java
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        
        queue.put(1);
        queue.put(2);
        queue.put(3);
        
        System.out.println("ArrayBlockingQueue: " + queue); // Output: [1, 2, 3]
        
        queue.take(); // Removes 1
        System.out.println("After take: " + queue);  // Output: [2, 3]
    }
}
```

### LinkedBlockingQueue Class
`LinkedBlockingQueue` is a blocking queue that can optionally have a capacity bound. It uses linked nodes to store elements and is thread-safe, making it ideal for concurrent applications.

#### Key characteristics of `LinkedBlockingQueue`
- **Bounded or unbounded**: The queue can have a fixed capacity, or it can be unbounded (the capacity grows dynamically).
- **Thread-safe**: All operations are thread-safe.
- **Blocking operations**: The `put()` and `take()` methods block if the queue is full or empty, respectively.

#### Example
```java
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
        
        queue.put("First");
        queue.put("Second");
        
        System.out.println("Queue: " + queue);
        
        queue.take();  // Removes "First"
        System.out.println("After take: " + queue);
    }
}
```

# Summary of Key Implementations

| Data Structure      | Description                                               | Key Characteristics                                                   |
|---------------------|-----------------------------------------------------------|-----------------------------------------------------------------------|
| **Queue**           | First-in, first-out collection                            | Insertion at the end, retrieval from the head.                        |
| **Deque**           | Double-ended queue                                        | Can insert and retrieve from both ends.                               |
| **PriorityQueue**    | Priority-based queue                                      | Orders elements based on priority rather than insertion order.        |
| **ArrayBlockingQueue** | Thread-safe, bounded queue backed by an array           | Fixed size, blocking operations when full or empty.                   |
| **LinkedBlockingQueue** | Thread-safe, optionally bounded queue using linked nodes | Supports large capacity, blocking operations when full or empty.      |
| **ArrayDeque**       | Resizable array-backed deque                             | Faster than `LinkedList` for stack and queue operations.              |
| **LinkedList**       | Implements both `List` and `Deque`                       | Can be used as a list or deque, slower than `ArrayDeque`.             |

# Key Takeaways
- **Queue** and **Deque** are fundamental data structures for managing ordered data.
- **BlockingQueue** is ideal for concurrent applications where threads must wait for the queue to be in a usable state.
- **PriorityQueue** is perfect for cases where elements must be processed based on priority rather than insertion order.
- **ArrayBlockingQueue** and **LinkedBlockingQueue** provide thread-safe, blocking implementations, with

`ArrayBlockingQueue` being bounded and `LinkedBlockingQueue` offering optional capacity.

By understanding these data structures and their use cases, you can effectively manage tasks like scheduling, concurrency, and priority-based processing in Java.
