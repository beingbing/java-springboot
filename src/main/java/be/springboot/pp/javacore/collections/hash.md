### Hashing and Hash-based Structures in Java

Hashing is a fundamental concept in computer science that powers many of Java's core data structures, like `HashMap`, `HashSet`, and `Hashtable`. It allows efficient data storage and retrieval by mapping keys to specific locations using a hash function. Understanding hashing is crucial to using and optimizing these structures.

---

### **Objective:**
Delve deeper into how hashing works in Java, focusing on hash-based structures like `HashMap` and `HashSet`. This will cover topics such as:
1. **Hash functions and collision resolution.**
2. **Customizing `hashCode()` and `equals()` methods.**
3. **Resizing and load factors in `HashMap`.**

---

### 1. **Hash Functions and Collision Resolution**

#### **What is a Hash Function?**

A **hash function** is a function that takes an input (like a key) and returns a fixed-size integer (called a **hash code**) that represents the input. In the context of Java, every object has a `hashCode()` method that produces an integer. Hash-based data structures use this integer to determine where to place an entry in memory (often in an array or bucket).

#### **How Hashing Works in Java**

When a key is added to a `HashMap` or `HashSet`:
- Java calls the **`hashCode()`** method on the key to produce a **hash code**.
- The hash code is used to compute the **bucket index** in an internal array, where the key-value pair or key is stored.

**Example of `hashCode()` in `HashMap`**:
```java
int hashCode = key.hashCode();
int bucketIndex = hashCode % arrayLength; // Array length is the internal storage size
```

This way, Java can quickly determine where a particular key or value is stored.

#### **Collisions**

A **collision** occurs when two keys have the same hash code or map to the same bucket index. For example:
- Two different objects could return the same `hashCode()`.
- The array may have fewer slots than the number of possible hash codes.

Java needs a **collision resolution mechanism** to handle these scenarios.

#### **Collision Resolution Techniques**

##### a. **Chaining (Linked List)**

The most common collision resolution mechanism in Java’s `HashMap` is **chaining**. When multiple keys map to the same bucket (i.e., collision), they are stored in a **linked list** or **binary tree** at that bucket.

- If a collision occurs, new elements are appended to the linked list or binary tree at the bucket.
- When searching for an element, Java scans the linked list at the corresponding bucket and compares the keys using `equals()`.

**Example of Chaining**:
```java
import java.util.HashMap;
import java.util.Map;

public class ChainingExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);   // hashCode() determines the bucket
        map.put("Banana", 2);  // Collision might occur, and "Banana" could be placed in the same bucket as "Apple"
        
        System.out.println("Map: " + map);
    }
}
```

##### b. **Open Addressing (Linear Probing, Quadratic Probing, Double Hashing)**

In **open addressing**, if a collision occurs, Java probes the array for the next available slot. This technique is less common in Java but is used in some other languages and data structures.

---

### 2. **Customizing `hashCode()` and `equals()`**

To ensure that custom objects can be used correctly in hash-based structures (`HashMap`, `HashSet`), it is crucial to override the `hashCode()` and `equals()` methods. These two methods determine how Java stores and retrieves objects from hash-based collections.

#### **The Contract Between `hashCode()` and `equals()`**

- If two objects are **equal**, their **hash codes must be equal**.
- If two objects have the **same hash code**, they may or may not be equal.

**Important rule**: Overriding `equals()` requires overriding `hashCode()` to maintain consistency.

#### **Implementing `hashCode()` and `equals()`**

The `hashCode()` method should produce a well-distributed and repeatable hash code for every object, based on the object's state. The `equals()` method should compare object states and return `true` if they are logically equivalent.

**Example**: Customizing `hashCode()` and `equals()` for a `Person` class.
```java
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    // Getters and toString omitted for brevity
}

public class HashingExample {
    public static void main(String[] args) {
        Person p1 = new Person("John", 30);
        Person p2 = new Person("John", 30);
        
        System.out.println(p1.equals(p2)); // true, based on state
        System.out.println(p1.hashCode() == p2.hashCode()); // true, due to consistent hashCode
    }
}
```

#### **Key Points**:
- Use a **prime number (e.g., 31)** when computing hash codes to reduce collisions.
- Always base `hashCode()` and `equals()` on **immutable fields** to ensure consistent behavior.

---

### 3. **Resizing and Load Factors in `HashMap`**

#### **Load Factor**

The **load factor** in a `HashMap` determines how full the internal array can get before it is resized. A common default value for the load factor is **0.75**, meaning when the map is **75% full**, it will resize itself.

- A **lower load factor** (e.g., 0.5) reduces collisions but increases memory usage.
- A **higher load factor** (e.g., 0.9) saves memory but increases the likelihood of collisions, which can lead to performance degradation.

#### **Resizing**

When the number of key-value pairs exceeds the **capacity × load factor** of a `HashMap`, Java resizes the internal array (typically **doubling** the array size) and rehashes all the existing keys into the new array.

**Example of Resizing**:
```java
import java.util.HashMap;
import java.util.Map;

public class ResizingExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4, 0.75f);  // Initial capacity = 4, load factor = 0.75
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);  // At this point, the map resizes to avoid exceeding the load factor

        System.out.println("Map size after resizing: " + map.size());  // Output: 4
    }
}
```

#### **Why Resizing is Important**

Resizing helps prevent too many collisions and ensures that the time complexity for operations like `put()` and `get()` remains close to **O(1)**. However, resizing is an expensive operation because every element in the `HashMap` must be rehashed into the new, larger array.

#### **Best Practices with Load Factor and Resizing**:
- **Use the default load factor (0.75)** for general-purpose maps.
- If you know the approximate size of the map ahead of time, you can **specify an initial capacity** to avoid frequent resizing.

---

### Summary of Hashing and Hash-based Structures in Java

- **Hashing** is the technique of using hash codes to map keys to bucket indices in a hash-based structure.
- **Collisions** are inevitable, and Java handles them through **chaining** (using linked lists or binary trees in `HashMap`).
- Custom objects used as keys in `HashMap` or `HashSet` must have properly implemented `hashCode()` and `equals()` methods to ensure correct behavior.
- **Load factor** determines when a `HashMap` resizes, and resizing ensures good performance by keeping collision rates low.

Next, we will move to **Graph-based Data Structures in Java**. Let me know when you're ready to proceed!