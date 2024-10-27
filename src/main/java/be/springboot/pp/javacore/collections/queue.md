# Queue Interface
The `Queue` interface represents an ordered collection that holds elements whose insertion and retrieval/access happens in a first-in, first-out (FIFO) manner. We can not peek/access elements in between of the collection.

## Characteristics of a `Queue`
- **FIFO Order**: The element inserted first is removed first.
- **Null Handling**: Most implementations of `Queue` do not allow `null` elements.
- **Common Methods**:
  - `offer(E e)`: Inserts an element, returning true if successful or false if the queue is full.
  - `add(E e)`: Inserts an element, returning true if successful or throws exception if the queue is full.
  - `peek()`: retrieves, but does not remove the head, returning null if the queue is empty.
  - `element()`: retrieves, but does not remove the head, throws exception if the queue is empty.
  - `poll()`: retrieves and removes the head, returning null if the queue is empty.
  - `remove()`: retrieves and removes the head, throws exception if the queue is empty.

## Implementations: PriorityQueue
The `PriorityQueue` is a special kind of queue where elements are ordered based on their priority rather than their insertion order. The elements are ordered either according to their natural ordering or by a `Comparator` provided at queue creation.

### Distinct characteristics of `PriorityQueue`
- **Non-FIFO behavior**: It does not guarantee FIFO behavior.
- **Not thread-safe**: It is not synchronized and should be used carefully in concurrent environments.
- **Null Handling**: It does not allow `null` elements.

### Example
```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
  public static void main(String[] args) {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    priorityQueue.offer(20);
    priorityQueue.offer(10);
    priorityQueue.offer(30);

    System.out.println("PriorityQueue: " + priorityQueue);  // Output: PriorityQueue: [10, 20, 30]

    System.out.println("Polling: " + priorityQueue.poll()); // Output: Polling: 10
    // Removes and returns the head (10)

    System.out.println("After polling: " + priorityQueue);  // Output: After polling: [20, 30]
  }
}
```

# Deque Interface
The `Deque` (double-ended queue) interface extends `Queue` and allows elements to be inserted or removed from both ends of the queue. It can function as both a queue (FIFO) and a stack (LIFO).

## Key characteristics of a `Deque`
- **Double-ended**: Elements can be added and removed from both the head and the tail.
- **Null Handling**: Most implementations do not permit `null` elements.
- **Common Methods**:
    - `offerFirst(E e)`, `offerLast(E e)`: Inserts an element at the front or the end of the deque, returns true if successful or false if the dequeue is full.
    - `addFirst(E e)`, `addLast(E e)`: Inserts an element at the front or the end of the deque, returns true if successful or false if the dequeue is full.
    - `peekFirst()`, `peekLast()`: Retrieve, but do not remove, the first or last element. Returning null if the queue is empty.
    - `getFirst()`, `getLast()`: Retrieve, but do not remove, the first or last element. Throws exception if the queue is empty.
    - `pollFirst()`, `pollLast()`: Remove elements from the front or the end. Returns null if the queue is empty.
    - `removeFirst()`, `removeLast()`: Remove elements from the front or the end. Throws an exception if the queue is empty.

## Mimicking stack
- **push(E e)**: Equivalent to `addFirst()`, inserts the element at the head of the deque.
- **pop()**: Equivalent to `removeFirst()`, removes and returns the first element.
- **peek()**: Equivalent to `peekFirst()`, retrieves but does not remove the first element.

## Common Deque Implementations
- **ArrayDeque**: A resizable array implementation of the `Deque` interface. It is faster than `LinkedList` for both stack and queue operations and has no capacity restrictions.
- **LinkedList**: Also implements `Deque`, allowing operations on both ends of the list.

## Implementation: ArrayDeque
`ArrayDeque` is a resizable array implementation of the `Deque` interface. It is efficient for both insertions and deletions at the front and back of the deque.

### Advantages
- No capacity restrictions (other than memory limits).
- Amortized constant time complexity for most operations (O(1)).
- Can be used both as a stack and a queue.

### Disadvantages
- Not thread-safe (needs external synchronization for concurrent access).

### Example
```java
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) {
		Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
        System.out.println(deque); // Output: [1, 2]
        
        deque.removeFirst(); // Removes 1
        deque.removeLast();  // Removes 2
        System.out.println(deque); // Output: []
	}
}
```

## Specialized Deque Implementations
- **ConcurrentLinkedDeque**: It is a thread-safe, lock-free implementation of a deque. It is ideal for use in concurrent environments where multiple threads access the deque simultaneously.
### Advantages
- Non-blocking and lock-free, providing thread safety with high performance under concurrency.
### Disadvantages
- Higher complexity due to concurrency control.
- Slightly slower than non-concurrent implementations like ArrayDeque or LinkedList in single-threaded scenarios.

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
