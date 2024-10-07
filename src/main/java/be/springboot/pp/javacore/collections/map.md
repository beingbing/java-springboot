### Map-based Data Structures in Java

Map-based data structures in Java store **key-value pairs**, where each key is associated with exactly one value. Java’s `Map` interface provides the framework for such collections, enabling fast lookups, inserts, and updates based on keys. Unlike `Set` or `List`, a `Map` allows keys and values, and keys must be unique.

#### Overview of the `Map` Interface
#### Implementations of `Map`:
- **HashMap**
- **LinkedHashMap**
- **TreeMap**
- **EnumMap**
- **WeakHashMap**
- **IdentityHashMap**
#### Comparison of `Map` Implementations
#### Key Features of `Map` Operations
#### Applications of `Map` (Caching, Lookup tables)

---

### 1. Overview of the `Map` Interface

The **`Map` interface** in Java, part of the `java.util` package, represents a collection of key-value pairs, where each key is unique, and each key maps to exactly one value. The primary purpose of a `Map` is to facilitate fast lookups and retrievals based on keys.

#### Key Features:
- Keys are **unique**, and a single key can map to only one value.
- **Values** can be duplicated.
- **Null keys and values** are allowed, depending on the implementation (e.g., `HashMap` allows one `null` key, while `TreeMap` does not allow `null` keys).

**Common Methods in the `Map` Interface**:
- **`put(K key, V value)`**: Associates the specified value with the specified key.
- **`get(Object key)`**: Returns the value associated with the specified key.
- **`remove(Object key)`**: Removes the key-value pair for the specified key.
- **`containsKey(Object key)`**: Checks if the map contains the specified key.
- **`containsValue(Object value)`**: Checks if the map contains the specified value.
- **`size()`**: Returns the number of key-value pairs.
- **`isEmpty()`**: Checks if the map is empty.
- **`clear()`**: Removes all key-value pairs from the map.

---

### 2. Implementations of `Map`

#### a. **HashMap**

`HashMap` is the most commonly used `Map` implementation. It is backed by a **hash table**, which ensures constant-time (`O(1)`) performance for the basic operations: `put()`, `get()`, `remove()`, and `containsKey()`.

**Key Features of `HashMap`**:
- Keys are **unordered**.
- Allows **one `null` key** and **multiple `null` values**.
- Not synchronized (i.e., not thread-safe without external synchronization).

**Example**:
```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Orange", 3);
        
        System.out.println("Map: " + map);  // Output: {Apple=1, Banana=2, Orange=3}
        
        // Retrieving a value
        System.out.println("Value for key 'Banana': " + map.get("Banana"));  // Output: 2
    }
}
```

#### b. **LinkedHashMap**

`LinkedHashMap` is a subclass of `HashMap` that maintains a **doubly linked list** of the entries, preserving the **insertion order** of key-value pairs.

**Key Features of `LinkedHashMap`**:
- Keys are **ordered by insertion order**.
- Allows **one `null` key** and **multiple `null` values**.
- Slightly slower than `HashMap` due to the overhead of maintaining the linked list.

**Example**:
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Orange", 3);
        
        System.out.println("LinkedHashMap: " + map);  // Output: {Apple=1, Banana=2, Orange=3}
    }
}
```

#### c. **TreeMap**

`TreeMap` implements the `NavigableMap` interface and is backed by a **Red-Black Tree**, which keeps the keys **sorted** in their natural order or a custom comparator.

**Key Features of `TreeMap`**:
- Keys are **sorted** (natural ordering or custom comparator).
- Does **not allow `null` keys**.
- Operations like `put()`, `get()`, and `remove()` take **O(log n)** time due to the tree structure.

**Example**:
```java
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("Banana", 2);
        map.put("Apple", 1);
        map.put("Orange", 3);
        
        System.out.println("TreeMap (sorted): " + map);  // Output: {Apple=1, Banana=2, Orange=3}
    }
}
```

#### d. **EnumMap**

`EnumMap` is a specialized implementation for **enum keys**. It is highly efficient, using a compact array internally to represent the map.

**Key Features of `EnumMap`**:
- Keys must be of **enum type**.
- More efficient than other `Map` implementations when using enum keys.
- **Does not allow `null` keys** but allows `null` values.

**Example**:
```java
import java.util.EnumMap;
import java.util.Map;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}

public class EnumMapExample {
    public static void main(String[] args) {
        Map<Day, String> map = new EnumMap<>(Day.class);
        map.put(Day.MONDAY, "Work");
        map.put(Day.FRIDAY, "Relax");
        
        System.out.println("EnumMap: " + map);  // Output: {MONDAY=Work, FRIDAY=Relax}
    }
}
```

#### e. **WeakHashMap**

`WeakHashMap` is a `Map` implementation where the **keys are weakly referenced**. This means that when a key is no longer referenced elsewhere in the application, the entry can be garbage collected.

**Key Features of `WeakHashMap`**:
- Keys are weak references, meaning they can be **garbage collected** when no longer in use.
- Useful for caching and memory-sensitive applications.
- **Allows `null` keys**.

**Example**:
```java
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapExample {
    public static void main(String[] args) {
        Map<Object, String> map = new WeakHashMap<>();
        Object key1 = new Object();
        Object key2 = new Object();
        
        map.put(key1, "Value1");
        map.put(key2, "Value2");
        
        key1 = null;  // The key1 object is now weakly reachable
        
        System.gc();  // Hint to the JVM to perform garbage collection
        
        System.out.println("WeakHashMap: " + map);  // Depending on GC, output could exclude key1
    }
}
```

#### f. **IdentityHashMap**

`IdentityHashMap` compares keys using **reference equality** (`==`) instead of **object equality** (`equals()`), meaning two keys are considered equal only if they are the same object.

**Key Features of `IdentityHashMap`**:
- Keys are compared using `==` (reference equality).
- Allows **`null` keys**.
- Used when reference equality matters (e.g., interning or unique object instances).

**Example**:
```java
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapExample {
    public static void main(String[] args) {
        Map<String, String> map = new IdentityHashMap<>();
        String key1 = new String("Key");
        String key2 = new String("Key");
        
        map.put(key1, "Value1");
        map.put(key2, "Value2");  // Not considered a duplicate because keys are different objects
        
        System.out.println("IdentityHashMap: " + map);  // Output: {Key=Value1, Key=Value2}
    }
}
```

---

### 3. Comparison of `Map` Implementations

| **Implementation**    | **Key Ordering**        | **Null Handling**         | **Performance**                      | **Use Case**                                  |
|-----------------------|-------------------------|---------------------------|--------------------------------------|-----------------------------------------------|
| **HashMap**            | No ordering             | Allows one `null` key, multiple `null` values | `O(1)` for `put()`, `get()`, `remove()` | Fast lookups without any specific ordering   |
| **LinkedHashMap**      | Insertion order         | Allows one `null` key, multiple `null` values | Slightly slower than `HashMap` due to linked list | Use when order of insertion matters          |
| **TreeMap**            | Sorted (natural or custom) | Does not allow `null`

keys, allows `null` values | `O(log n)` for `put()`, `get()`, `remove()` | When sorted key order is important           |
| **EnumMap**            | Enum-based ordering     | Does not allow `null` keys, allows `null` values | Fastest for enum keys                       | Use with enum keys for highly efficient lookups |
| **WeakHashMap**        | No ordering             | Allows `null` keys and values                | Depends on garbage collection               | Use when keys should be garbage collected    |
| **IdentityHashMap**    | No ordering             | Allows `null` keys and values                | Similar to `HashMap`                        | Use when reference equality matters (==)     |

---

### 4. Key Features of `Map` Operations

- **Fast lookup and retrieval** based on keys (e.g., `HashMap` provides O(1) lookup).
- **Iteration over keys, values, and entries**: `Map` provides methods to iterate over the keys, values, or key-value pairs (`entrySet()`).
- **Key ordering**: Use specific implementations like `TreeMap` or `LinkedHashMap` when key order matters.
- **Weak references**: Use `WeakHashMap` when keys need to be garbage collected.

---

### 5. Applications of `Map`

#### a. **Caching**:
`Map` implementations like `WeakHashMap` are useful for memory-sensitive caching mechanisms where you want to cache objects but allow them to be garbage collected when memory is tight.

#### b. **Lookup Tables**:
`HashMap` and `TreeMap` are ideal for implementing lookup tables where you need fast retrieval of values based on a key.

#### c. **Counters**:
`Map` can be used to count occurrences of items in a list. For example, counting word frequencies in a document can be implemented using a `HashMap<String, Integer>`.

---

### Summary of Map-based Data Structures in Java

- The `Map` interface defines collections of key-value pairs.
- There are multiple implementations of `Map`, each optimized for different use cases (e.g., `HashMap`, `TreeMap`, `WeakHashMap`).
- Operations like **put**, **get**, and **remove** are fundamental to all `Map` implementations.
- Choosing the right `Map` implementation depends on your requirements for key ordering, null handling, and performance.

Next, we'll move on to **Graph-based Data Structures**. Let me know when you're ready to continue!